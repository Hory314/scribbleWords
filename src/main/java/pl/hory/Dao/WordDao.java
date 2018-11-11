package pl.hory.Dao;

import pl.hory.Entity.Word;
import pl.hory.Service.DBService;
import pl.hory.Service.DaoService;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WordDao
{
    private String dbName = "scribble_words";
    private String tableName = "polish";

    public int count()
    {
        String query = "select COUNT(*) AS count from " + tableName;

        try
        {
            List<Map<String, String>> result = DBService.executeSelectQuery(dbName, query, null);
            return Integer.parseInt(result.get(0).get("count"));
        }
        catch (SQLException e)
        {
            System.out.println(e);
        }

        return 0;
    }

    public List<Word> findAll()
    {

        String query = "Select id, word, add_date, INET_NTOA(adder_ip) AS adder_ip, accepted, reject_reason, review_date from " + tableName/* + "WHERE `accepted` = 'yes'"*/;
        try
        {
            List<Map<String, String>> result = DBService.executeSelectQuery(dbName, query, null);
            List<Word> words = new ArrayList<>();

            if (result.size() > 0)
            {
                for (Map<String, String> row : result)
                {
                    Word word = createWord(row);
                    words.add(word);
                }
                return words;
            }
        }
        catch (SQLException e)
        {
            System.out.println(e);
        }
        return null;
    }

    public Word getById(Integer id)
    {
        String query = "Select id, word, add_date, INET_NTOA(adder_ip) AS adder_ip, accepted, reject_reason, review_date from " + tableName + " where `id`=?";
        List<String> params = new ArrayList<>();
        params.add(id.toString());

        try
        {
            List<Map<String, String>> result = DBService.executeSelectQuery(dbName, query, params);
            if (result.size() > 0)
            {
                Map<String, String> resultWord = result.get(0);

                Word word = createWord(resultWord);

                return word;
            }
        }
        catch (SQLException e)
        {
            System.out.println(e);
        }
        return null;
    }

    public Word getByWord(String word)
    {
        String query = "Select id, word, add_date, INET_NTOA(adder_ip) AS adder_ip, accepted, reject_reason, review_date from " + tableName + " where `word` = ?";
        List<String> params = new ArrayList<>();
        params.add(word);

        try
        {
            List<Map<String, String>> result = DBService.executeSelectQuery(dbName, query, params);
            if (result.size() > 0)
            {
                Map<String, String> resultWord = result.get(0);

                Word newWord = createWord(resultWord);

                System.out.println(newWord);
                return newWord;
            }
        }
        catch (SQLException e)
        {
            System.out.println(e);
        }
        return null;
    }

    private Word createWord(Map<String, String> row)
    {
        Word word = new Word();

        word.setId(Integer.parseInt(row.get("id")));
        word.setWord(row.get("word"));
        word.setAddDate(row.get("add_date"));
        word.setAdderIp(row.get("adder_ip"));
        word.setAccepted(row.get("accepted"));
        word.setRejectReason(row.get("reject_reason"));
        word.setReviewDate(row.get("review_date"));

        return word;
    }

    public void save(Word word)
    {
        if (word.getId() == null)
        {
            add(word);
        }
        else
        {
            update(word);
        }
    }

    private void add(Word word)
    {
        String query = "Insert into " + tableName + " values (null, ?, ?, INET_ATON(?), ?, ?, ?)"; // pamietac o kolejnosci dodawania do listy params

        List<String> params = new ArrayList<>();
        params.add(word.getWord());
        params.add(word.getAddDate());
        params.add(word.getAdderIp());
        params.add(word.getAccepted());
        params.add(word.getRejectReason());
        params.add(word.getReviewDate());
        DaoService.setNewId(word, query, params, dbName);
    }

    private void update(Word word)
    {

        String query = "Update " + tableName + " Set `word` = ?, `add_date` = ?, `adder_ip` = INET_ATON(?), `accepted` = ?, `reject_reason` = ?, `review_date` = ? where `id` = ?";

        List<String> params = new ArrayList<>();
        params.add(word.getWord());
        params.add(word.getAddDate());
        params.add(word.getAdderIp());
        params.add(word.getAccepted());
        params.add(word.getRejectReason());
        params.add(word.getReviewDate());
        params.add(word.getId().toString());

        try
        {
            DBService.executeQuery(dbName, query, params);
        }
        catch (SQLException e)
        {
            //should be logger - save info about error
            System.out.println(e);
        }
    }

    public boolean delete(Integer id)
    {
        return DaoService.delete(id, tableName, dbName);
    }
}
