package com.Brendon;


import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

    static final String DB_CONNECTION_URL = "jdbc:mysql://localhost:3306/";
    static final String USER = "brendon";
    static final String PASSWORD = "password";

    public static String DBName = "";

    static Statement statement = null;
    static Connection conn = null;
    public static ResultSet resSetDriver = null; // Driver resultset
    public static ResultSet resSetPickUpr = null;// pickup orders resultset
    public static ResultSet resSetDropOff = null;// dropoff orders resultset




    public static void main(String[] args) {

        try {

            DBName = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
            int checkDatabse = statement.executeUpdate("create new database if not exists " + DBName);

        } catch (SQLException SQLE) {

            System.out.println("Something went wrong with creating the database");
            SQLE.printStackTrace();
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


            conn = DriverManager.getConnection(DB_CONNECTION_URL + DBName,USER,PASSWORD);
            statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

            String newTable = "create table if not exits Drivers(Driver ID int, Starting Location varchar(60))";


        }

    }


}
