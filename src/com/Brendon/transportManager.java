package com.Brendon;


import java.sql.*;
import java.util.LinkedList;

public class transportManager {

    static final String DB_CONNECTION_URL = "jdbc:mysql://localhost:3306/";
    static final String USER = "brendon";
    static final String PASSWORD = "password";

    public static String DBName = "Driver_Order_Records";
    public static String DriverTable = "DriverTable";
    public static String PickupTable = "PickUpTable";
    public static String DeliveryTable = "DeliveryTable";


    public static Statement statementDriver = null;
    public static Statement statementPU = null;
    public static Statement statementDel = null;

    static Connection conn = null;

    public static ResultSet resSetDriver = null; // Driver resultset
    public static ResultSet resSetPickUp = null;// pickup orders resultset
    public static ResultSet resSetDel = null;// dropoff orders resultset

    public static PreparedStatement prepInsert; // for driver


    private static DriverModel TransportTable;


    public static void main(String[] args) {


        if (!setup()) {
            System.out.println("Problem setting up the program. Quitting.");
            System.exit(-1);
        }


        if (!loadTables()) {
            System.out.println("Problem loading table. Quitting.");
            System.exit(-1);
        }

        driverMain driver = new driverMain(TransportTable);
    }

    /*
    This block of code will load tables into the respective Resultsets.
     */
    public static boolean loadTables() {

        try {

            if (resSetDriver != null) {
                resSetDriver.close();
            }
            if (resSetPickUp != null) {
                resSetPickUp.close();
            }
            if (resSetDel != null) {
                resSetDel.close();
            }


            String loadDriver = "SELECT * FROM " + DriverTable;
            resSetDriver = statementDriver.executeQuery(loadDriver);

            if (TransportTable == null) {

                TransportTable = new DriverModel(resSetDriver);

            } else {

                TransportTable.UpdateRS(resSetDriver);
            }



            String loadPU = "SELECT * FROM " + PickupTable;
            resSetPickUp = statementPU.executeQuery(loadPU);

            String loadDEL = "SELECT * FROM " + DeliveryTable;
            resSetDel = statementDel.executeQuery(loadDEL);

            return true;

        } catch (Exception E) {

            System.out.println("error loading tables");
            E.printStackTrace();
            return false;
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
            statementDriver = conn.createStatement(resSetDriver.TYPE_SCROLL_INSENSITIVE, resSetDriver.CONCUR_UPDATABLE);
            statementPU = conn.createStatement(resSetPickUp.TYPE_SCROLL_INSENSITIVE, resSetPickUp.CONCUR_UPDATABLE);
            statementDel = conn.createStatement(resSetDel.TYPE_SCROLL_INSENSITIVE, resSetDel.CONCUR_UPDATABLE);



            /*
            This next block of code sets up the initial Driver Table of each driver and
            where they begin their days.
             */
            if (!driverTableCheck()) {


                String newTable = "create table " + DriverTable + " (DriverID int, StartingLocation varchar(60), PRIMARY KEY (DriverID))";
                statementDriver.executeUpdate(newTable);


                String prestate = "insert into " + DriverTable + " values ( ? , ? )";
                prepInsert = conn.prepareStatement(prestate);

                //TODO add the remaining drivers.
                prepInsert.setInt(1, 1888833);
                prepInsert.setString(2, "3634 Cecilia Circle, Edina MN");
                prepInsert.executeUpdate();
            }

            if (!pickUpTableCheck()) {

                String newTable = "create table " + PickupTable + " (OrderNum int, Address varchar(60), ContactName varchar(60), " +
                        "Pieces int, TotalWeight double, DriverID int, PRIMARY KEY(OrderNum))";
                System.out.println(newTable);
                statementPU.executeUpdate(newTable);
            }

            if (!delTableCheck()) {

                String newTable = "CREATE TABLE " + DeliveryTable + " (OrderNum int, Address varchar(60), ContactName varchar(60), " +
                        "Pieces INT , TotalWeight DOUBLE, DriverID int, PRIMARY KEY(OrderNum))";
                System.out.println(newTable);
                statementDel.executeUpdate(newTable);

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
        ResultSet tablesRS = statementDriver.executeQuery(checkDriverTable);
        if (tablesRS.next()) {
            return true;
        }
        return false;

    }


    private static boolean pickUpTableCheck() throws SQLException { // checks for pickup table

        String checkDriverTable = "SHOW TABLES LIKE '" + PickupTable + "'";
        ResultSet tablesRS = statementPU.executeQuery(checkDriverTable);
        if (tablesRS.next()) {
            return true;
        }
        return false;

    }

    private static boolean delTableCheck() throws SQLException { // checks for delivery table

        String checkDriverTable = "SHOW TABLES LIKE '" + DeliveryTable + "'";
        ResultSet tablesRS = statementDel.executeQuery(checkDriverTable);
        if (tablesRS.next()) {
            return true;
        }
        return false;

    }

    public static void close() {

        try {

            if (resSetDriver != null || resSetPickUp != null || resSetDel != null) {

                resSetDriver.close();
                resSetPickUp.close();
                resSetDel.close();
            }

            if (statementDriver != null || statementPU != null || statementDel != null) {

                statementDriver.close();
                statementPU.close();
                statementDel.close();
            }

            if (conn != null) {

                conn.close();
            }


        } catch (SQLException SQLE) {

            System.out.println("problems closing connections");
            SQLE.printStackTrace();
        }
    }

}


