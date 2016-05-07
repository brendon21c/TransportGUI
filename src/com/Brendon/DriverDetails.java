package com.Brendon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;


public class DriverDetails extends JFrame implements WindowListener {
    private JSpinner spinner1;
    private JButton getStuffForDateButton;
    private JTable table1;
    private JButton displayDetailsOfOrderButton;
    private JTextArea pickupTextArea;
    private JTextArea deliveryTextArea;
    private JPanel DriverDetailRoot;
    private JButton newOrderButton;


    DriverDetails(PickUpModel PUM){

        setContentPane(DriverDetailRoot);
        setLocation(300,300);
        setSize(500,500);
        pack();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addWindowListener(this);

        table1.setModel(PUM);
        table1.setGridColor(Color.black);
        table1.getColumnModel().getColumn(0).setWidth(500);



    }





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
