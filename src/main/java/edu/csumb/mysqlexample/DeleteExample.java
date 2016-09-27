package edu.csumb.mysqlexample;

import java.sql.PreparedStatement;

/**
 * Created by ndavidson on 9/27/16.
 */
public class DeleteExample {
    public static boolean delete(int id) {
        try {
            PreparedStatement stm = DBConnector.getConn().prepareStatement("DELETE FROM `info` WHERE `id`=?");
            stm.setInt(1, id);
            stm.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
