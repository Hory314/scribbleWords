package pl.hory.Controller.Admin;

import pl.hory.Dao.WordDao;
import pl.hory.Entity.Word;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "List", urlPatterns = {"/adminpanel/list", "/adminpanel/list/"})
public class List extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        WordDao wordDao = new WordDao();
        java.util.List<Word> words = null;
        int acceptedConut;

        request.setAttribute("words", words);
        getServletContext().getRequestDispatcher("/WEB-INF/views/admin/list.jsp").forward(request, response);
    }
}
