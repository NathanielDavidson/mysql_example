package edu.csumb.mysqlexample;

import java.sql.PreparedStatement;

/**
 * Created by ndavidson on 9/27/16.
 */
public class UpdateExample {
    public static boolean update(int id, String data){
        try{
            PreparedStatement stm = DBConnector.getConn().prepareStatement("UPDATE `info` SET `data`=? WHERE `id`=?");
            stm.setString(1, data);
            stm.setInt(2, id);
            stm.execute();
            stm.close();
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
