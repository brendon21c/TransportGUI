package com.Brendon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;



public class driverMain extends JFrame implements WindowListener {


    private JPanel driverRoot;
    private JTable driverTable;
    private JButton selectDriverButton;
    private JButton quitButton;
    private JButton addDriverButton;


    driverMain(final DriverModel TTM ) {

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
