package pl.hory.Controller;

import pl.hory.Dao.WordDao;
import pl.hory.Entity.Word;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.InetAddress;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@WebServlet(name = "Main", urlPatterns = {""})
public class Main extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Map<String, String> incorrectWords = new HashMap<>();
        WordDao wordDao = new WordDao();

        String newWords = request.getParameter("new_words");
        StringTokenizer st = new StringTokenizer(newWords, ",");
        while (st.hasMoreTokens())
        {
            String word = st.nextToken().toLowerCase();
            System.out.println("Parsed word: " + word);

            // System.out.println("Pobrane z DB = " + wordDao.getByWord(word).getWord() + " =? " + word + " Pobrane z param");

            if (word.length() < 3)
            {
                System.out.println("Słowo jest za krótkie." + word);
                incorrectWords.put(word, "Słowo jest za krótkie.");
            }
            else if (word.length() > 32)
            {
                System.out.println("Słowo jest za długie." + word);
                incorrectWords.put(word, "Słowo jest za długie.");
            }
            else if (!word.matches("^[A-Za-zżźćńółęąśŻŹĆĄŚĘŁÓŃ -]*$"))
            {
                System.out.println("Słowo zawiera niedozwolone znaki." + word);
                incorrectWords.put(word, "Słowo zawiera niedozwolone znaki.");
            }
            else if (false/*wordDao.getByWord(word).getWord().*/) // todo :: npe here
            {
                System.out.println("Słowo zostało już dodane." + word);
                incorrectWords.put(word, "Słowo zostało już dodane.");
            }
            else // slowo ok
            {

                Word newWord = new Word();

                LocalDateTime now = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

                //todo :: test this on openshift
                String ip = request.getRemoteAddr();
                if (ip.equalsIgnoreCase("0:0:0:0:0:0:0:1"))
                {
                    InetAddress inetAddress = InetAddress.getLocalHost();
                    String ipAddress = inetAddress.getHostAddress();
                    ip = ipAddress;
                }
                System.out.println(ip);

                newWord.setWord(word);
                newWord.setAddDate(now.format(formatter));
                newWord.setAdderIp(ip);
                newWord.setAccepted("no");

                wordDao.save(newWord);
            }
        }
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        WordDao wordDao = new WordDao();
        List<Word> words = wordDao.findAll();
        int count = wordDao.count();

        request.setAttribute("words", words);
        request.setAttribute("words_count", count);
        getServletContext().getRequestDispatcher("/WEB-INF/views/main.jsp").forward(request, response);
    }
}
