package com.Brendon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;


public class driverMain extends JFrame implements WindowListener {


    private JPanel driverRoot;
    private JTable driverTable;
    private JButton selectDriverButton;
    private JButton quitButton;
    private JButton addDriverButton;



    driverMain(final DriverModel TTM) {

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
                System.out.println(row);
                int col = driverTable.getSelectedColumn();
                System.out.println(col);


            }
        });

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                transportManager.close();
                System.exit(0);
            }
        });



    }

    public void insertDriver(int ID, String Loc)  {

        try {


            transportManager.prepInsert.setInt(1, ID);
            transportManager.prepInsert.setString(2, Loc);

        } catch (SQLException E) {

            System.out.println("problems entering driver info");;
            E.printStackTrace();
        }

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
