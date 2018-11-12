package pl.hory.Service;

import pl.hory.Entity.Word;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoService
{
    public static void setNewId(Object obj, String query, List<String> params, String dbName) throws SQLException
    {
        try
        {
            Integer newId = DBService.executeInsert(dbName, query, params);
            if (newId != null)
            {
                if (obj instanceof Word)
                {
                    ((Word) obj).setId(newId);
                }
            }
        }
        catch (SQLException e)
        {
            //should be logger - save info about error
            System.out.println(e);
            throw e;
        }
    }

    // delete (by id)  is universal for all Daos
    public static boolean delete(Integer id, String tableName, String dbName)
    {
        try
        {
            String query = "Delete From " + tableName + " where `id`=?";
            List<String> params = new ArrayList<>();
            params.add(id.toString());

            DBService.executeQuery(dbName, query, params);
            return true;
        }
        catch (SQLException e)
        {
            return false;
        }
    }
}
