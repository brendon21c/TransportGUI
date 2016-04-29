package com.Brendon;


import com.sun.xml.internal.bind.v2.TODO;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;

public class transportManager {

    static final String DB_CONNECTION_URL = "jdbc:mysql://localhost:3306/";
    static final String USER = "brendon";
    static final String PASSWORD = "password";

    public static String DBName = "Driver_Order_Records";
    public static String DriverTable = "DriverTable";

    static Statement statement = null;
    static Connection conn = null;

    public static ResultSet resSetDriver = null; // Driver resultset
    public static ResultSet resSetPickUpr = null;// pickup orders resultset
    public static ResultSet resSetDropOff = null;// dropoff orders resultset
    public static PreparedStatement prepInsert;


    public LinkedList<Integer> Drivers = new LinkedList<Integer>();


    public static void main(String[] args) {


        if (!setup()) {
            System.out.println("Problem setting up the program. Quitting.");
            System.exit(-1);
        }


    }


    public static boolean setup() {

        try {


            try {

                String driver = "com.mysql.jdbc.Driver";
                Class.forName(driver);

            } catch (ClassNotFoundException CNFE) {

                System.out.println("Sorry, no driver found");
                CNFE.printStackTrace();
                System.exit(-1);
            }


            conn = DriverManager.getConnection(DB_CONNECTION_URL + DBName, USER, PASSWORD);
            statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);



            /*
            This next block of code sets up the initial Driver Table of each driver and
            where they begin their days.
             */
            if (!driverTableCheck()) {


                String newTable = "create table " + DriverTable + " (DriverID int, StartingLocation varchar(60), PRIMARY KEY (DriverID))";
                statement.executeUpdate(newTable);


                String prestate = "insert into " + DriverTable + " values ( ? , ? )";
                prepInsert = conn.prepareStatement(prestate);

                //TODO add the remaining drivers.
                prepInsert.setInt(1, 1888833);
                prepInsert.setString(2, "3634 Cecilia Circle, Edina MN");
                prepInsert.executeUpdate();
            }

            return true;

        } catch (SQLException SQLE) {
            System.out.println("Danger Will Robinson!");
            SQLE.printStackTrace();
            System.exit(-1);
            return false;
        }
    }

    /*
    This will check if the driver table exists.
     */
    private static boolean driverTableCheck() throws SQLException {

        String checkDriverTable = "SHOW TABLES LIKE '" + DriverTable + "'";
        ResultSet tablesRS = statement.executeQuery(checkDriverTable);
        if (tablesRS.next()) {
            return true;
        }
        return false;

    }
}
