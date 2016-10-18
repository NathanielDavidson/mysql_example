package edu.csumb.mysqlexample;

import java.io.PrintStream;
import java.sql.PreparedStatement;
import java.sql.Statement;

/**
 * Created by ndavidson on 9/27/16.
 */
public class Executor {

    private static final String TABLE_NAME = "info";

    public static void execute(PrintStream out){
        try {

            DBController rdsDB = new DBController(); // connect to the mysql server

            IdDataTable table = new IdDataTable(TABLE_NAME, rdsDB);

            table.dropTable();
            table.createTable();

        /*
                                             _
                                            | |
                          ___ _ __ _   _  __| |
                         / __| '__| | | |/ _` |
                        | (__| |  | |_| | (_| |
                         \___|_|   \__,_|\__,_|

        */

            int id = table.insert("ping");
            if (id != -1) {
                System.out.println("Created row!");
            }

            String data = table.get(id);
            if (data != null) {
                System.out.println("value from database row [" + id + "]: " + data);
            } else {
                System.out.println("Data not found in the database!");
            }

            if (table.update(id, "pong")) {
                System.out.println("Updated row");
            } else {
                System.out.println("Failed to update row!");
            }

            data = table.get(id);
            if (data != null) {
                System.out.println("value from database row [" + id + "]: " + data);
            } else {
                System.out.println("Data not found in the database!");
            }

            if (table.delete(id)) {
                System.out.println("Deleted the row [" + id + "]");
            } else {
                System.out.println("Failed to delete the row[" + id + "]");
            }

            data = table.get(id);
            if (data != null) {
                System.out.println("value from database row [" + id + "]: " + data);
            } else {
                System.out.println("Data not found in the database!");
            }

            table.dropTable();
            rdsDB.close();
        }catch (Exception e){

        }
    }
    public static void main(String[] args){
        execute(System.out);
    }
}
