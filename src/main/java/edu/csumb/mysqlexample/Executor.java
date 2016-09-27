package edu.csumb.mysqlexample;

import java.sql.PreparedStatement;
import java.sql.Statement;

/**
 * Created by ndavidson on 9/27/16.
 */
public class Executor {

    public static void main(String[] args){
        DBConnector.initialize();

        /*
                                             _
                                            | |
                          ___ _ __ _   _  __| |
                         / __| '__| | | |/ _` |
                        | (__| |  | |_| | (_| |
                         \___|_|   \__,_|\__,_|

        */

        int id = InsertExample.insert("ping");
        if(id!=-1) {
            System.out.println("Created row!");
        }

        String data = SelectExample.get(id);
        if(data!=null){
            System.out.println("value from database row ["+id+"]: "+data);
        }else{
            System.out.println("Data not found in the database!");
        }

        if(UpdateExample.update(id, "pong")){
            System.out.println("Updated row");
        }else{
            System.out.println("Failed to update row!");
        }

        data = SelectExample.get(id);
        if(data!=null){
            System.out.println("value from database row ["+id+"]: "+data);
        }else{
            System.out.println("Data not found in the database!");
        }

        if(DeleteExample.delete(id)){
            System.out.println("Deleted the row ["+id+"]");
        }else{
            System.out.println("Failed to delete the row["+id+"]");
        }

        data = SelectExample.get(id);
        if(data!=null){
            System.out.println("value from database row ["+id+"]: "+data);
        }else{
            System.out.println("Data not found in the database!");
        }

        DBConnector.close();
    }
}
