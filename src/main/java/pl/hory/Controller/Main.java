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
        int successCount = 0;
        int failCount = 0;

        String newWords = request.getParameter("new_words");
        StringTokenizer st = new StringTokenizer(newWords, ",");
        while (st.hasMoreTokens())
        {
            String word = st.nextToken().trim();
            //System.out.println("Parsed word: " + word);

            // System.out.println("Pobrane z DB = " + wordDao.getByWord(word).getWord() + " =? " + word + " Pobrane z param");
            Word wordInDB = wordDao.getByWord(word);

            if (word.length() < 3)
            {
                System.out.println("Słowo jest za krótkie: " + word);
                incorrectWords.put(word, "Słowo jest za krótkie");
                failCount++;
            }
            else if (word.length() > 32)
            {
                System.out.println("Słowo jest za długie: " + word);
                incorrectWords.put(word, "Słowo jest za długie");
                failCount++;
            }
            else if (!word.matches("^[A-Za-zżźćńółęąśŻŹĆĄŚĘŁÓŃ -]*$"))
            {
                System.out.println("Słowo zawiera niedozwolone znaki: " + word);
                incorrectWords.put(word, "Słowo zawiera niedozwolone znaki");
                failCount++;
            }
            else if (wordInDB != null)
            {
                System.out.println("Słowo zostało już dodane: " + word);
                String msg;

                if (wordInDB.getAccepted().equals("yes"))
                {
                    msg = "Słowo jest już zaakceptowane";
                }
                else
                {
                    if (wordInDB.getRejectReason() != null)
                    {
                        msg = "Słowo odrzucone z powodu: " + wordInDB.getRejectReason();
                    }
                    else
                    {
                        msg = "Słowo jest już dodane i oczekuje na weryfikację";
                    }
                }
                incorrectWords.put(word, msg);
                failCount++;
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
                // System.out.println(ip);

                newWord.setWord(word);
                newWord.setAddDate(now.format(formatter));
                newWord.setAdderIp(ip);
                newWord.setAccepted("no");

                wordDao.save(newWord);
                successCount++;
            }
        }

        request.setAttribute("successCount", successCount);
        request.setAttribute("failCount", failCount);
        request.setAttribute("incorrectWords", incorrectWords);
        request.setAttribute("postResult", true);
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        WordDao wordDao = new WordDao();
        List<Word> words = wordDao.findAll("where `accepted` = 'yes' ORDER BY RAND()");
        int count = wordDao.count("where `accepted` = 'yes'");

        request.setAttribute("words", words);
        request.setAttribute("words_count", count);
        getServletContext().getRequestDispatcher("/WEB-INF/views/main.jsp").forward(request, response);
    }
}
