package org.fasttrackit.dev.lesson1.numgenerator.servlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by user376 on 22.08.2015.
 */
public class DataBase {
    private int nr;
    public DataBase(int nr)
    {
        this.nr=nr;
    }
    public void demoCreate() throws ClassNotFoundException, SQLException {

        System.out.println("incep");
        // 1. load driver
        Class.forName("org.postgresql.Driver");

        // 2. define connection params to db
        final String URL = "jdbc:postgresql://54.93.65.5:5432/Dan_Agenda";
        final String USERNAME = "fasttrackit_dev";
        final String PASSWORD = "fasttrackit_dev";

        // 3. obtain a connection
        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        System.out.println("am obtinut conex");
        // 4. create a query statement
        PreparedStatement pSt = conn.prepareStatement("INSERT INTO \"Numar\" (number) VALUES (?)");
        pSt.setInt(1,nr);

        System.out.println("statement");
        // 5. execute a prepared statement
        int rowsInserted = pSt.executeUpdate();

        System.out.println("executat");
        // 6. close the objects
        pSt.close();
        conn.close();
        System.out.println("gata");
    }

}
