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
    private JButton deleteDriverButton;


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
                transportManager.showDriverInfo(driverID);

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
