package pl.hory.Filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.Enumeration;
import java.util.Properties;

@WebFilter(filterName = "EncodingFilter", urlPatterns = {"/*"})
public class EncodingFilter implements Filter
{
    // export JAVA_TOOL_OPTIONS='-Dfile.encoding="UTF-8"'
    public void destroy()
    {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException
    {
        Properties properties = java.lang.System.getProperties();
        properties.setProperty("file.encoding", "UTF-8");
        System.out.println("Java property: file.encoding=" + properties.getProperty("file.encoding"));
        // JAVA PROPERTIES

        // PAGES
        req.setCharacterEncoding("UTF-8");
        //resp.setContentType("text/html; charset=UTF-8");//do NOT set this. Encodes files like .css .js etc. also to text/html!!!
        resp.setCharacterEncoding("UTF-8");

        System.err.println(LocalDateTime.now() + ": " + this.getClass().getSimpleName() + " executed.\nzażółć gęślą jaźń\nZAŻÓŁĆ GĘŚLĄ JAŹŃ");

        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException
    {

    }

}
