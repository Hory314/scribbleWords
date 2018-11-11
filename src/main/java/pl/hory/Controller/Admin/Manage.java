package pl.hory.Controller.Admin;

import pl.hory.Dao.WordDao;
import pl.hory.Entity.Word;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
        System.out.println("reject reason: " + rejectReason + ".");

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        try
        {
            WordDao wordDao = new WordDao();
            Word word = wordDao.getById(Integer.parseInt(wordId));

            if (rejectReason.equals("")) // jak puste to slowo zaakceptowane
            {
                word.setAccepted("yes");
            }
            else // odrzucone
            {
                word.setAccepted("no");
                word.setRejectReason(rejectReason);
            }
            word.setReviewDate(now.format(formatter));
            wordDao.save(word); // update
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
        List<Word> words = wordDao.findAll("WHERE `accepted` = 'no' AND `reject_reason` IS NULL");
        int toCheckCount = wordDao.count("WHERE `accepted` = 'no' AND `reject_reason` IS NULL");

        request.setAttribute("words", words);
        request.setAttribute("count", toCheckCount);
        getServletContext().getRequestDispatcher("/WEB-INF/views/admin/manage.jsp").forward(request, response);
    }
}
