package com.Brendon;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class driverEntry extends JFrame {
    private JTextField driverName;
    private JTextField location;
    private JButton updateButton;
    private JPanel driverEntryPanel;

    private DriverModel DM;


    driverEntry(final DriverModel DM) {

        setContentPane(driverEntryPanel);
        setLocation(300, 300);
        setSize(500, 500);
        pack();
        setVisible(true);

        /*updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int driverNum = Integer.parseInt(driverName.getText());
                String driverLoctaion = location.getText();

                DM.insertDriver(driverNum,driverLoctaion);

            }
        });

    } */


    }
}
