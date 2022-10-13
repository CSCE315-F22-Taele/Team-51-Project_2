package com.involuntary.revpos.database;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Johnny Le
 */
public class DatabaseConnection {
    public Connection databaseLink;

    /**
     * Establishes a connection to the database through psql.jar
     *
     * @return the Connection Object from the database
     */
    public Connection getConnection() {
        String teamNumber = "51";
        String sectionNumber = "904";
        String dbName = "csce331_" + sectionNumber + "_" + teamNumber;
        String dbConnectionString = "jdbc:postgresql://csce-315-db.engr.tamu.edu/" + dbName;

        try {
            Class.forName("org.postgresql.Driver");
            databaseLink = DriverManager.getConnection(dbConnectionString, dbSetup.user, dbSetup.pswd);
        } catch (Exception ex) {
            System.err.println(ex.getClass().getName()+": "+ex.getMessage());
            return null;
        }

        return databaseLink;
    }

}
