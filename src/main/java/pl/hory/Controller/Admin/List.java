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

@WebServlet(name = "List", urlPatterns = {"/adminpanel/list", "/adminpanel/list/"})
public class List extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String edit = request.getParameter("edit");
        String wordId = request.getParameter("word_id");
        String formWord = request.getParameter("word");
        Integer intWordId = null;
        try
        {
            intWordId = Integer.parseInt(wordId);
        }
        catch (NumberFormatException e)
        {
            e.printStackTrace();
        }
        WordDao wordDao = new WordDao();
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        if (edit.equals("1")) // edycja
        {
            if (WordService.isCorrect(formWord) && (wordDao.getByWord(formWord) == null)) // jesli nie znaleziono takiego slowa w bazie)
            {
                Word word = wordDao.getById(intWordId);
                word.setReviewDate(now.format(formatter));
                word.setWord(formWord);
                try
                {
                    wordDao.save(word); // update
                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                }
                //  request.setAttribute("error", "Zedytowano");
            }
            else
            {
                request.setAttribute("error", "Słowo już istnieje lub zawiera błędy.");
                doGet(request, response);
                return;
            }
        }
        else // usuwanie
        {
            wordDao.delete(intWordId);
            // request.setAttribute("error", "Usunięto");
        }
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        WordDao wordDao = new WordDao();
        java.util.List<Word> words = null;
        int acceptedCount;

        try
        {
            words = wordDao.findAll("WHERE `accepted` = 'yes' ORDER BY id DESC");
            acceptedCount = wordDao.count("WHERE `accepted` = 'yes'");
        }
        catch (SQLException e)
        {
            System.out.println(e);
            e.printStackTrace();
            acceptedCount = 0;
            request.setAttribute("error", "Niepowodzenie. Brak połączenia z bazą danych. Proszę powiadomić administratora.");
        }

        request.setAttribute("words", words);
        request.setAttribute("count", acceptedCount);
        getServletContext().getRequestDispatcher("/WEB-INF/views/admin/list.jsp").forward(request, response);
    }
}
