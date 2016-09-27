package edu.csumb.mysqlexample;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by ndavidson on 9/27/16.
 */
public class InsertExample {
    public static int insert(String data){
        int id = 0;
        try{
            PreparedStatement stm = DBConnector.getConn().prepareStatement("INSERT INTO `info` (`id`, `data`) VALUES (NULL, ?)", Statement.RETURN_GENERATED_KEYS);
            stm.setString(1, data);
            stm.execute();
            ResultSet rs = stm.getGeneratedKeys();
            if(rs.next()){
                id = rs.getInt(1);
            }
            stm.close();
            rs.close();
            return id;
        }catch(Exception e){
            e.printStackTrace();
        }
        return -1;
    }
}
