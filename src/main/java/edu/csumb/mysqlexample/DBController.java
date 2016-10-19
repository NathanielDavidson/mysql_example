package edu.csumb.mysqlexample;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by ndavidson on 9/27/16.
 */
public class DBController {
    private Connection conn=null;
    private static final String CREDENTIALS_FILE_PATH = "./access.ini";
    private static final String DRIVER = "mysql";
    public  DBController() throws IOException{

        HashMap<String, String> settings = new HashMap<String, String>();

        settings.put("RDS_HOST", fetch("RDS_HOST"));
        settings.put("RDS_DB", fetch("RDS_DB"));
        settings.put("RDS_USER", fetch("RDS_USER"));
        settings.put("RDS_PASS", fetch("RDS_PASS"));

        try {
            conn = DriverManager.getConnection(
                "jdbc:"+DRIVER+"://"+settings.get("RDS_HOST")+"/" +
                settings.get("RDS_DB")+"?"+
                "user="+settings.get("RDS_USER")+"&"+
                "password="+settings.get("RDS_PASS")+"&"+
                "useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public DBController(String host, String database, String username, String password){
        try {
            conn = DriverManager.getConnection("jdbc:"+DRIVER+"://"+host+"/"+database+"?user="+username+"&password="+password+"&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public Connection getConn(){
        return conn;
    }
    public void close(){
        try {
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static Properties prop = null;
    public static String fetch(String key) throws IOException{ // get from Env vars, System properties, config file
        if (new File(CREDENTIALS_FILE_PATH).exists()) { //file stored properties
            if(prop==null) {
                prop = new Properties();
                prop.load(new FileInputStream(CREDENTIALS_FILE_PATH));
            }
            return prop.getProperty(key);
        } else if(System.getenv(key)!=null){ // environment variables
            return System.getenv(key);
        }else if(System.getProperty(key)!=null){
            return System.getProperty(key);
        }else{
            return null;
        }
    }
}