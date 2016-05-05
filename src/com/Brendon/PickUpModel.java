package com.Brendon;

import javax.swing.table.AbstractTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;



/*
This is the manager for the Pickup Table.
 */
public class PickUpModel extends AbstractTableModel {

    private int rowCountPU;
    private int colCountPU;

    ResultSet resSetPickUp;


    public PickUpModel(ResultSet resDriver) {

        resSetPickUp = resDriver;
        setup();

    }

    public void setup() {

        rowcount();

        try {

            colCountPU = resSetPickUp.getMetaData().getColumnCount();

        } catch (SQLException SQLE) {

            System.out.println("error");
            SQLE.printStackTrace();
        }
    }

    public void UpdateRS (ResultSet newSet) {

        resSetPickUp = newSet;
        setup();

    }

    /*
    This will loop over the table and return the number of rows and reset the "cursor"
     */
    public void rowcount() {

        rowCountPU = 0;

        try {

            resSetPickUp.beforeFirst();

            while (resSetPickUp.next()) {

                rowCountPU++;
            }

            resSetPickUp.beforeFirst();

        } catch (SQLException e) {

            System.out.println("error in rows");
            e.printStackTrace();
        }

    }


    public boolean insertDriver(int driver, String location) {


        try {

            resSetPickUp.moveToInsertRow();
            resSetPickUp.updateInt(1, driver);
            resSetPickUp.updateString(2, location);
            resSetPickUp.insertRow();
            resSetPickUp.moveToCurrentRow();
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

            resSetPickUp.absolute(row + 1);
            resSetPickUp.deleteRow();
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
        return rowCountPU;
    }

    @Override
    public int getColumnCount() {
        return colCountPU;
    }


    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        try {

            resSetPickUp.absolute(rowIndex + 1); // + 1 because the table begins at 0.

            Object item = resSetPickUp.getObject(columnIndex + 1);

            return item.toString();

        } catch (SQLException s) {

            System.out.println("error");
            s.printStackTrace();
            return s.toString();
        }

    }


}



