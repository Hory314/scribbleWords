package pl.hory.Filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@WebFilter(filterName = "EncodingFilter", urlPatterns = "/*")
public class EncodingFilter implements Filter
{
    public void destroy()
    {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException
    {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");

        HttpServletRequest request = (HttpServletRequest)req; // full request
        HttpServletResponse response = (HttpServletResponse) resp; // full response

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");

        System.err.println(LocalDateTime.now() + ": " + this.getClass().getSimpleName() + " executed.");

        chain.doFilter(request, response);
    }

    public void init(FilterConfig config) throws ServletException
    {

    }

}
