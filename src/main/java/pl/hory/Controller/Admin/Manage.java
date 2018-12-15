package pl.hory.Controller.Admin;

import pl.hory.Dao.WordDao;
import pl.hory.Entity.Word;
import pl.hory.Service.WordService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@WebServlet(name = "Manage", urlPatterns = {"/adminpanel/manage", "/adminpanel/manage/"})
public class Manage extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String wordId = request.getParameter("word_id");
        String rejectReason = request.getParameter("reject_reason");
        String formWord = request.getParameter("word");
        String accept= request.getParameter("accept");
        System.out.println("reject reason: " + rejectReason + ".");
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        try
        {
            WordDao wordDao = new WordDao();
            Word word = wordDao.getById(Integer.parseInt(wordId));

            if (accept.equals("1")) //accept = 1 - admin kliknął Akceptuj
            {
                if (wordDao.getByWord(formWord) == null /*&& isCorrect(formWord)*/) // jesli nie znaleziono takiego slowa w bazie
                {
                    if (WordService.isCorrect(formWord))
                    {
                        word.setWord(formWord);
                        word.setAccepted("yes");
                    }
                    else
                    {
                        request.setAttribute("error", "Słowo już istnieje lub zawiera błędy.");
                        doGet(request, response);
                        return;
                    }
                }
                else // istnieje , ale...
                {
                    if (wordDao.getByWord(formWord).getId().equals(word.getId())) // jesli ID slowa z formularz z bazy == słowu o ID z hidden to puszczam
                    {
                        // slowo zostaje
                        word.setAccepted("yes");
                    }
                    else
                    {
                        request.setAttribute("error", "Słowo już istnieje lub zawiera błędy.");
                        doGet(request, response);
                        return;
                    }
                }
            }
            else // odrzucone przez admina
            {
                word.setAccepted("no");
                word.setRejectReason(rejectReason);
            }
            word.setReviewDate(now.format(formatter));

            try
            {
                wordDao.save(word); // update
            }
            catch (SQLException e)
            {
                System.out.println(e);
                e.printStackTrace();
            }
        }
        catch (NumberFormatException e)
        {
            System.out.println("id nie jest liczbą!");
        }
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        WordDao wordDao = new WordDao();
        List<Word> words = null;
        int toCheckCount = 0;

        try
        {
            words = wordDao.findAll("WHERE `accepted` = 'no' AND `reject_reason` IS NULL");
            toCheckCount = wordDao.count("WHERE `accepted` = 'no' AND `reject_reason` IS NULL");
        }
        catch (SQLException e)
        {
            System.out.println(e);
            e.printStackTrace();
            toCheckCount = 0;
            request.setAttribute("error", "Niepowodzenie. Brak połączenia z bazą danych. Proszę powiadomić administratora.");
        }
        request.setAttribute("words", words);
        request.setAttribute("count", toCheckCount);
        getServletContext().getRequestDispatcher("/WEB-INF/views/admin/manage.jsp").forward(request, response);
    }
}
