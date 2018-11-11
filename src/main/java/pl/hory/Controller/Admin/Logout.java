package pl.hory.Controller.Admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "UserLogout", urlPatterns = {"/adminpanel/logout", "/adminpanel/logout/"})
public class Logout extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession(false);

        if (session != null)
        {
            session.invalidate();
        }
        request.setAttribute("login_info", "<p style='color: green;'>Zostałeś wylogowany.</p>"); // mozna potem zmienic na template
        System.out.println("Wylogowałem");

        String redirect = "/adminpanel";

        getServletContext().getRequestDispatcher(redirect).forward(request, response);
    }
}
