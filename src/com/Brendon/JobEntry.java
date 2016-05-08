package com.Brendon;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class jobEntry extends JFrame {
    private JTextField addressPU;
    private JTextField contactPU;
    private JTextField pieces;
    private JTextField totalWeight;
    private JTextField addressDel;
    private JTextField contactDel;
    private JButton addToRouteButton;
    private JTextField DriverID;
    private JTextField dateEntry;
    private JPanel jobEntryRoot;


    jobEntry(driverMain parent) {

        setContentPane(jobEntryRoot);
        setLocation(500,300);
        setSize(2000, 2000);
        pack();
        setVisible(true);
        System.out.println(transportManager.orderNum);



        addToRouteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // these are for the pick up table.
                String addressPickUp = addressPU.getText();
                String contactPickUp = contactPU.getText();
                int boxNum = Integer.parseInt(pieces.getText());
                double weightPU = Double.parseDouble(totalWeight.getText());

                //These are for the delivery table
                String addressDelivery = addressDel.getText();
                String delContact = contactDel.getText();

                int driver = Integer.parseInt(DriverID.getText());
                String date = dateEntry.getText();

                System.out.println(transportManager.orderNum);

                // These insert the information into the respective tables
                transportManager.insertPickUp(transportManager.orderNum, addressPickUp, contactPickUp, boxNum, weightPU, driver, date);
                transportManager.insertDel(transportManager.orderNum, addressDelivery, delContact, boxNum, weightPU, driver, date);

                transportManager.orderNum++;
                System.out.println(transportManager.orderNum);
                jobEntry.this.dispose();

            }
        });

    }

}
