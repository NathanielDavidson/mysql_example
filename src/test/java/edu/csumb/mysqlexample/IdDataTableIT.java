package edu.csumb.mysqlexample;

import org.junit.*;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created by nathanieldavidson on 10/13/16.
 */
public class IdDataTableIT {
    private static final String TABLE_NAME = "movie_dev";
    private DBController controller;
    private IdDataTable table;
    @Before
    public void ConnectToDB() throws IOException{
        controller = new DBController();
        table = new IdDataTable(TABLE_NAME, controller);
        table.dropTable();
        table.createTable();
    }
    @After
    public void disconnect(){
        table.dropTable();
        controller.close();
    }

    /*
        Create movie wait for confirmation from AWS
     */
    @Test
    public void createNewMovie(){
        assertNotEquals(table.insert("Deadpool"), -1);
    }


    /*
        Create movie and then check to see if the movie exists by comparing dataStored
     */
    @Test
    public void createAndVerifyMovie(){
        int id = table.insert("Frozen");

        String data = table.get(id);

        assertTrue(data.equals("Frozen"));
    }

    /*
        Create movie, then delete the movie, then make sure that the row does not exist
     */
    @Test
    public void deletedMovie(){
        int id = table.insert("The Accountant");

        table.delete(id);

        assertNull(table.get(id));
    }

    /*
        Create movie entry, update dataStored, make sure the update held
     */
    @Test
    public void updateMovieRecordConfirm(){
        int id = table.insert("Terminator");

        table.update(id, "Terminator 2");

        String data = table.get(id);
        assertNotNull(data);
        assertTrue(data.equals("Terminator 2"));
    }
}