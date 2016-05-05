package com.Brendon;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class driverEntry extends JFrame {
    private JTextField driverID;
    private JTextField location;
    private JButton updateButton;
    private JPanel driverEntryPanel;



    driverEntry(DriverModel TTM) {

        setContentPane(driverEntryPanel);
        setLocation(300, 300);
        setSize(500, 500);
        pack();
        setVisible(true);

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int id = Integer.parseInt(driverID.getText());
                String loc = location.getText();

                TTM.insertDriver(id, loc);
                driverEntry.this.dispose();


            }
        });


    }


    }

