package com.Brendon;

import javax.swing.table.AbstractTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
This is the data manager for the driver table.
 */

public class DriverModel extends AbstractTableModel {

    private int rowCountDriver;
    private int colCountDriver;

    ResultSet resSetDriver;
    
    /*
    This makes sure that when called from the main it updates itself.
     */
    public DriverModel(ResultSet resDriver) {

        resSetDriver = resDriver;
        setup();

    }

    /*
    Starts the setup of counting rows and columns.
     */
    public void setup() {

        rowcount();

        try {

           colCountDriver = resSetDriver.getMetaData().getColumnCount();

        } catch (SQLException SQLE) {

            System.out.println("error");
            SQLE.printStackTrace();
        }
    }



    /*
    Updates the table when called.
     */
    public void UpdateRS (ResultSet newSet) {

        resSetDriver = newSet;
        setup();

    }

    /*
    This will loop over the table and return the number of rows and reset the "cursor"
     */
    public void rowcount() {

        rowCountDriver = 0;

        try {

            resSetDriver.beforeFirst();

            while (resSetDriver.next()) {

                rowCountDriver++;
            }

            resSetDriver.beforeFirst();

        } catch (SQLException e) {

            System.out.println("error in rows");
            e.printStackTrace();
        }

    }


    public boolean insertDriver(int driver, String location) {


        try {

            resSetDriver.moveToInsertRow();
            resSetDriver.updateInt(1, driver);
            resSetDriver.updateString(2, location);
            resSetDriver.insertRow();
            resSetDriver.moveToCurrentRow();
            fireTableDataChanged();
            return true;

        } catch (SQLException SQLE) {

            System.out.println("troubling adding information to driver table");
            SQLE.printStackTrace();
            return false;
        }
    }

    public boolean deleteDriver(int row) {

        try {

            resSetDriver.absolute(row + 1);
            resSetDriver.deleteRow();
            fireTableDataChanged();
            return true;

        } catch (SQLException e) {

            System.out.println("problem deleting");
            e.printStackTrace();
            return false;
        }
    }



    @Override
    public int getRowCount() {
        rowcount();
        return rowCountDriver;
    }

    @Override
    public int getColumnCount() {
        return colCountDriver;
    }


    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        try {

            resSetDriver.absolute(rowIndex + 1); // + 1 because the table begins at 0.

            Object item = resSetDriver.getObject(columnIndex + 1);

            return item.toString();

        } catch (SQLException s) {

            System.out.println("error");
            s.printStackTrace();
            return s.toString();
        }

    }


}



