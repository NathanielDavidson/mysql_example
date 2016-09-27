package edu.csumb.mysqlexample;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by ndavidson on 9/27/16.
 */
public class DBConnector {
    public static Connection conn;
    public static void initialize(){
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/test?user=root&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void close(){
        try {
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static Connection getConn() {
        return conn;
    }
}
