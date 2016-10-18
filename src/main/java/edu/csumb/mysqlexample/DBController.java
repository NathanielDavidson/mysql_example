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

        settings.put("RDS_HOST","");
        settings.put("RDS_DB","");
        settings.put("RDS_USER","");
        settings.put("RDS_PASS","");

        if (new File(CREDENTIALS_FILE_PATH).exists()) { //file stored properties

            Properties prop = new Properties();
            prop.load(new FileInputStream(CREDENTIALS_FILE_PATH));
            for(Map.Entry<String, String> setting:settings.entrySet()){
                settings.put( setting.getKey(), prop.getProperty(setting.getKey()) );
            }

        } else if(System.getenv("RDS_HOST")!=null){ // environment variables

            for(Map.Entry<String, String> setting:settings.entrySet()){
                settings.put( setting.getKey(), System.getenv(setting.getKey()) );
            }

        }else if(System.getProperty("RDS_HOST")!=null){

            for(Map.Entry<String, String> setting:settings.entrySet()){
                settings.put( setting.getKey(), System.getProperty(setting.getKey()) );
            }

        }else{
            throw new IOException();
        }

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
}