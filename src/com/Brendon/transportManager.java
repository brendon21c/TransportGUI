package com.Brendon;


import java.sql.*;
import java.util.Date;

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
    public static PreparedStatement prepPU; // for Pick Up table
    public static PreparedStatement prepDel; // for delivery table


    private static DriverModel TransportTable;
    private static PickUpModel PU_Table;
    private static deliveryModel delModel;

    public static int DriverID_key;
    public static int orderNum;



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

            if (PU_Table == null) {

                PU_Table = new PickUpModel(resSetPickUp);

            } else {

                PU_Table.UpdateRS(resSetPickUp);
            }


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

            /*
            creating connections and setting up statements for each table.
             */
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

                prepInsert.setInt(1, 1888833);
                prepInsert.setString(2, "3634 Cecilia Circle, Edina MN");
                prepInsert.executeUpdate();
            }

            if (!pickUpTableCheck()) {

                String newTable = "create table " + PickupTable + " (OrderNum int, Address varchar(60), ContactName varchar(60), " +
                        "Pieces int, TotalWeight double, DriverID int, OrderDate VARCHAR(20), PRIMARY KEY(OrderNum))";
                System.out.println(newTable);
                statementPU.executeUpdate(newTable);
            }

            if (!delTableCheck()) {

                String newTable = "CREATE TABLE " + DeliveryTable + " (OrderNum int, Address varchar(60), ContactName varchar(60), " +
                        "Pieces INT , TotalWeight DOUBLE, DriverID int, OrderDate VARCHAR(20) , PRIMARY KEY(OrderNum))";
                System.out.println(newTable);
                statementDel.executeUpdate(newTable);

            }

            String orderNumtemp = "select max(OrderNum) from " + PickupTable;
            statementPU.execute(orderNumtemp);
            ResultSet test = statementPU.getResultSet();

            if (test.next()) {

                orderNum = test.getInt(1);
            }

            if (orderNum == 0) {

                orderNum = 100;

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

    /*
    This will check if the pick up table exists.
     */
    private static boolean pickUpTableCheck() throws SQLException { // checks for pickup table

        String checkDriverTable = "SHOW TABLES LIKE '" + PickupTable + "'";
        ResultSet tablesRS = statementPU.executeQuery(checkDriverTable);
        if (tablesRS.next()) {
            return true;
        }
        return false;

    }

    /*
    This will check if the delivery table exists.
     */
    private static boolean delTableCheck() throws SQLException { // checks for delivery table

        String checkDriverTable = "SHOW TABLES LIKE '" + DeliveryTable + "'";
        ResultSet tablesRS = statementDel.executeQuery(checkDriverTable);
        if (tablesRS.next()) {
            return true;
        }
        return false;

    }


    public static void showDriverInfo(int driverID, String reportDate) {

        try {

            // These Queries are not working.
            String loadPU = "SELECT * FROM " + PickupTable + " WHERE DriverID = ? AND  OrderDate = ? ";
            PreparedStatement psPickUp = conn.prepareStatement(loadPU);
            psPickUp.setInt(1, driverID);
            psPickUp.setString(2, reportDate);

            resSetPickUp = statementPU.executeQuery(loadPU);



            String loadDel = "SELECT * FROM " + DeliveryTable + " WHERE DriverID = ? AND OrderDate = ?";
            PreparedStatement psDelivery = conn.prepareStatement(loadDel);
            psDelivery.setInt(1, driverID);
            psDelivery.setString(2, reportDate);

            resSetDel = statementDel.executeQuery(loadDel);


            if (delModel == null || PU_Table == null) {

                delModel = new deliveryModel(resSetDel);
                PU_Table = new PickUpModel(resSetPickUp);

            } else {

                PU_Table.UpdateRS(resSetPickUp);
                delModel.UpdateRS(resSetDel);

                driverDetails DD = new driverDetails(PU_Table); // Another problem here is I don;t know how to work with both result sets in the next window.
            }


        } catch (SQLException e) {

            e.printStackTrace();
        }



    }

    /*
   Inserts information into the pickup table, called from jobEntry.
    */
    public static void insertPickUp(int orderNum, String address, String contact, int boxes, double weight,
     int driverID, String date) {

        try {

            String insertPU = "insert into " + PickupTable + " values ( ? , ? , ? , ? , ? , ? , ? )";
            prepPU = conn.prepareStatement(insertPU);

            prepPU.setInt(1, orderNum);
            prepPU.setString(2, address);
            prepPU.setString(3, contact);
            prepPU.setInt(4, boxes);
            prepPU.setDouble(5, weight);
            prepPU.setInt(6, driverID);
            prepPU.setString(7, date);
            prepPU.executeUpdate();


            if (PU_Table == null) {

                PU_Table = new PickUpModel(resSetPickUp);

            } else {

                PU_Table.UpdateRS(resSetPickUp);
            }


        } catch (SQLException sqle) {

            sqle.printStackTrace();
        }

    }

    /*
    Inserts information into the delivery table, called from jobEntry.
     */
    public static void insertDel(int orderNum, String address, String contact, int boxes, double weight,
                                 int driverID, String date) {

        try {

            String insertDel = "insert into " + DeliveryTable + " values ( ? , ? , ? , ? , ? , ? , ? )";
            prepDel = conn.prepareStatement(insertDel);

            prepDel.setInt(1, orderNum);
            prepDel.setString(2, address);
            prepDel.setString(3, contact);
            prepDel.setInt(4, boxes);
            prepDel.setDouble(5, weight);
            prepDel.setInt(6, driverID);
            prepDel.setString(7, date);
            prepDel.executeUpdate();


            if (delModel == null) {

                delModel = new deliveryModel(resSetDel);

            } else {

                delModel.UpdateRS(resSetDel);
            }



        } catch (SQLException sqle) {

            sqle.printStackTrace();
        }

    }

    /*
  Closing all connections
   */
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


