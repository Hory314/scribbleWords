package pl.hory.Service;

import java.io.Serializable;

public class WordService implements Serializable
{
    public static boolean isCorrect(String word)
    {
        if (word.length() < 3)
        {
            return false;
        }
        else if (word.length() > 30)
        {
            return false;
        }
        else if (!word.matches("^[A-Za-zżźćńółęąśŻŹĆĄŚĘŁÓŃ -]*$"))
        {
            return false;
        }
       /* else if (wordInDB != null)
        {
            return false;
        }*/
        else // slowo ok
        {
            return true;
        }
    }
}