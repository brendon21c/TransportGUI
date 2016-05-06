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



    /*
        This makes sure that when called from the main it updates itself.
         */
    public PickUpModel(ResultSet resPickUp) {

        resSetPickUp = resPickUp;
        setup();

    }

    /*
    Starts the setup of counting rows and columns.
     */
    public void setup() {

        rowcount();

        try {

            colCountPU = resSetPickUp.getMetaData().getColumnCount();

        } catch (SQLException SQLE) {

            System.out.println("error");
            SQLE.printStackTrace();
        }
    }

    /*
    Updates the table when called
     */
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


    public boolean insertOrder(int OrderNum, String Address, String ContactName, int Pieces,
                               Double TotalWeight, int DriverID, String Date) {


        try {

            resSetPickUp.moveToInsertRow();
            resSetPickUp.updateInt(1, OrderNum);
            resSetPickUp.updateString(2,Address);
            resSetPickUp.updateString(3, ContactName);
            resSetPickUp.updateInt(4, Pieces);
            resSetPickUp.updateDouble(5, TotalWeight);
            resSetPickUp.updateInt(6, DriverID);
            resSetPickUp.updateString(7, Date);
            resSetPickUp.insertRow();
            resSetPickUp.moveToCurrentRow();
            fireTableDataChanged();
            return true;

        } catch (SQLException SQLE) {

            System.out.println("troubling adding information to pickup table");
            SQLE.printStackTrace();
            return false;
        }
    }


    /*
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
    } */





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



