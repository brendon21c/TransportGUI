package com.Brendon;

import javax.swing.table.AbstractTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;



/*
This is the manager for the delivery table.
 */
public class deliveryModel extends AbstractTableModel {

    private int rowCountPU;
    private int colCountPU;

    ResultSet resSetDel;



    /*
        This makes sure that when called from the main it updates itself.
         */
    public deliveryModel(ResultSet resDel) {

        resSetDel = resDel;
        setup();

    }

    /*
    Starts the setup of counting rows and columns.
     */
    public void setup() {

        rowcount();

        try {

            colCountPU = resSetDel.getMetaData().getColumnCount();

        } catch (SQLException SQLE) {

            System.out.println("error");
            SQLE.printStackTrace();
        }
    }

    /*
    Updates the table when called
     */
    public void UpdateRS (ResultSet newSet) {

        resSetDel = newSet;
        setup();

    }

    /*
    This will loop over the table and return the number of rows and reset the "cursor"
     */
    public void rowcount() {

        rowCountPU = 0;

        try {

            resSetDel.beforeFirst();

            while (resSetDel.next()) {

                rowCountPU++;
            }

            resSetDel.beforeFirst();

        } catch (SQLException e) {

            System.out.println("error in rows");
            e.printStackTrace();
        }

    }


    public boolean insertOrder(int OrderNum, String Address, String ContactName, int Pieces,
                               Double TotalWeight, int DriverID, String Date) {


        try {

            resSetDel.moveToInsertRow();
            resSetDel.updateInt(1, OrderNum);
            resSetDel.updateString(2,Address);
            resSetDel.updateString(3, ContactName);
            resSetDel.updateInt(4, Pieces);
            resSetDel.updateDouble(5, TotalWeight);
            resSetDel.updateInt(6, DriverID);
            resSetDel.updateString(7, Date);
            resSetDel.insertRow();
            resSetDel.moveToCurrentRow();
            fireTableDataChanged();
            return true;

        } catch (SQLException SQLE) {

            System.out.println("troubling adding information to pickup table");
            SQLE.printStackTrace();
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

            resSetDel.absolute(rowIndex + 1); // + 1 because the table begins at 0.

            Object item = resSetDel.getObject(columnIndex + 1);

            return item.toString();

        } catch (SQLException s) {

            System.out.println("error");
            s.printStackTrace();
            return s.toString();
        }

    }


}



