package edu.csumb.mysqlexample;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by Nathaniel on 10/17/2016.
 */
public class IdDataTable {
    private static final String KEY_COLUMN = "id";
    private static final String DATA_COLUMN = "data";
    private String tablename;
    private DBController controller;

    public IdDataTable(String tablename, DBController controller){
        this.tablename = tablename;
        this.controller = controller;
    }

    public String get(int id){
        String data = null;
        try{
            PreparedStatement stm = controller.getConn().prepareStatement("SELECT `"+DATA_COLUMN+"` FROM `"+tablename+"` WHERE `"+KEY_COLUMN+"`=?");
            stm.setInt( 1, id);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                data = rs.getString(DATA_COLUMN);
                break;
            }
            rs.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return data;
    }
    public boolean update(int id, String data){
        try{
            PreparedStatement stm = controller.getConn().prepareStatement("UPDATE `"+tablename+"` SET `"+DATA_COLUMN+"`=? WHERE `"+KEY_COLUMN+"`=?");
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
    public int insert(String data){
        int id = 0;
        try{
            PreparedStatement stm = controller.getConn().prepareStatement("INSERT INTO `"+tablename+"` (`"+KEY_COLUMN+"`, `"+DATA_COLUMN+"`) VALUES (NULL, ?)", Statement.RETURN_GENERATED_KEYS);
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
    public boolean delete(int id) {
        try {
            PreparedStatement stm = controller.getConn().prepareStatement("DELETE FROM `"+tablename+"` WHERE `"+KEY_COLUMN+"`=?");
            stm.setInt(1, id);
            stm.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteAll(){
        try {
            PreparedStatement stm = controller.getConn().prepareStatement("TRUNCATE TABLE `"+tablename+"`");
            stm.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean dropTable(){
        try {
            PreparedStatement stm = controller.getConn().prepareStatement("DROP TABLE `"+tablename+"`");
            stm.execute();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public boolean createTable(){
        try{
            PreparedStatement stm = controller.getConn().prepareStatement("CREATE TABLE  `"+tablename+"` (" +
                    "`"+KEY_COLUMN+"` INT NOT NULL AUTO_INCREMENT PRIMARY KEY ," +
                    "`"+DATA_COLUMN+"` VARCHAR(100) NOT NULL" +
                    ") ENGINE = INNODB;");
            stm.execute();
            stm.close();
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
