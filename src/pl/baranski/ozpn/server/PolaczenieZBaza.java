package pl.baranski.ozpn.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 
 * Klasa slużąca do obsługi połączenia z bazą danych.
 * 
 */
public class PolaczenieZBaza {

    public static Connection utworzPolaczenie() throws Exception {

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException cnfe) {
            System.out.println("Couldn't find the driver!");
            System.out.println("Let's print a stack trace, and exit.");
            cnfe.printStackTrace();
        }

        System.out.println("Registered the driver ok, so let's make a connection.");

        Connection c = null;

        try {
            c = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/ozpn", "postgres", "1111");
        } catch (SQLException se) {
            System.out.println("Couldn't connect: print out a stack trace and exit.");
            se.printStackTrace();
        }

        System.out.println("PolaczenieZBaza.utworzPolaczenie()");
        if (c != null)
            System.out.println("Hooray! We connected to the database!");
        else
            System.out.println("We should never get here.");

        System.out.println("Bartek");
        // }

        return c;
    }
}
