package com.Brendon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class driverMain extends JFrame implements WindowListener {


    private JPanel driverRoot;
    private JTable driverTable;
    private JButton selectDriverButton;
    private JButton quitButton;
    private JButton addDriverButton;
    private JButton deleteDriverButton;
    private JButton addJobButton;
    private JButton selectDateButton;
    private JTextField dateEntryField;

    public static String dateEntry;


    driverMain(DriverModel TTM) {

        setContentPane(driverRoot);
        setLocation(300,300);
        setSize(500,500);
        pack();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addWindowListener(this);

        driverTable.setModel(TTM);
        driverTable.setGridColor(Color.BLACK);
        driverTable.getColumnModel().getColumn(0).setWidth(500);

        // This whole section seems to cause problems when trying to load the table, so I'm going to use strings for the date
        /*
        Calendar earlyDate = Calendar.getInstance();
        earlyDate.set(2015,Calendar.JANUARY,1);
        System.out.println(earlyDate.getTime());

        Calendar lastdate = Calendar.getInstance();
        lastdate.set(2016, Calendar.DECEMBER,31);
        System.out.println(lastdate.getTime());

        dateSpinner.setModel(new SpinnerDateModel(lastdate.getTime(),earlyDate.getTime(),lastdate.getTime(),Calendar.DAY_OF_MONTH));
        */

        selectDateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                dateEntry = dateEntryField.getText();

            }
        });

        addDriverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                driverEntry entry = new driverEntry(TTM);


            }
        });

        selectDriverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {




                int row = driverTable.getSelectedRow();
                int driverID = Integer.parseInt(TTM.getValueAt(row, 0).toString());
                transportManager.showDriverInfo(driverID, dateEntry);

            }
        });

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                transportManager.close();
                System.exit(0);
            }
        });

        deleteDriverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int driver = driverTable.getSelectedRow();
                boolean delete = TTM.deleteDriver(driver);


            }
        });

        addJobButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                jobEntry newJob = new jobEntry(driverMain.this);

            }
        });

    }





    /*
    Overrides for WindowListener
     */

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
