package edu.csumb.mysqlexample;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by ndavidson on 9/27/16.
 */
public class SelectExample {
    public static String get(int id){
        String data = null;
        try{
            PreparedStatement stm = DBConnector.getConn().prepareStatement("SELECT `data` FROM `info` WHERE `id`=?");
            stm.setInt( 1, id);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                data = rs.getString("data");
                break;
            }
            rs.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return data;
    }
}
