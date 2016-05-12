package com.Brendon;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import com.google.maps.DirectionsApi;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DirectionsLeg;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.DirectionsStep;


public class driverDetails extends JFrame implements WindowListener {
    private JButton backButton;
    private JTable orderTable;
    private JButton displayDetailsOfOrderButton;
    private JTextArea pickupTextArea;
    private JTextArea deliveryTextArea;
    private JPanel DriverDetailRoot;
    private JLabel driverIdLabel;


    //Google Server Key: AIzaSyAJfHWId40N2-iYjOwOioguDIk0UQOUss8


    driverDetails(PickUpModel PUM){


        setContentPane(DriverDetailRoot);
        setLocation(300,300);
        setPreferredSize(new Dimension(1100,500));
        pack();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addWindowListener(this);

        /*
        This is for Google Maps WIP...
         */
        GeoApiContext context = new GeoApiContext();
        String key = "AIzaSyAJfHWId40N2-iYjOwOioguDIk0UQOUss8"; //todo

        context.setApiKey(key);

        orderTable.setModel(transportManager.pu_Table);
        driverIdLabel.setText(String.valueOf(driverMain.driverID));


        try {

            Icon backButtonImg = new  ImageIcon("Icon Files/BackButton.png");
            backButton.setIcon(backButtonImg);

        } catch (Exception e) {

            System.out.println("error with image file");;
            e.printStackTrace();

        }


        /*
        This block of code hides some of the order details to just show the order number and the date.
         */
        orderTable.getColumnModel().getColumn(1).setMinWidth(0);
        orderTable.getColumnModel().getColumn(1).setMaxWidth(0);
        orderTable.getColumnModel().getColumn(1).setWidth(0);
        orderTable.getColumnModel().getColumn(2).setMinWidth(0);
        orderTable.getColumnModel().getColumn(2).setMaxWidth(0);
        orderTable.getColumnModel().getColumn(2).setWidth(0);
        orderTable.getColumnModel().getColumn(3).setMinWidth(0);
        orderTable.getColumnModel().getColumn(3).setMaxWidth(0);
        orderTable.getColumnModel().getColumn(3).setWidth(0);
        orderTable.getColumnModel().getColumn(4).setMinWidth(0);
        orderTable.getColumnModel().getColumn(4).setMaxWidth(0);
        orderTable.getColumnModel().getColumn(4).setWidth(0);
        orderTable.getColumnModel().getColumn(5).setMinWidth(0);
        orderTable.getColumnModel().getColumn(5).setMaxWidth(0);
        orderTable.getColumnModel().getColumn(5).setWidth(0);


        orderTable.setGridColor(Color.white);
        orderTable.getColumnModel().getColumn(0).setWidth(500);


        // this button will "pull" the requested order information from
        // the pickup and delivery tables and display them in the proper fields.
        displayDetailsOfOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //These clear out the pickup and delivery text fields.
                pickupTextArea.setText("");
                deliveryTextArea.setText("");

                int row = orderTable.getSelectedRow();
                String newLine = "\n";


                /*
                This section is for the pickup table.
                 */
                int orderNumPU = Integer.parseInt(transportManager.pu_Table.getValueAt(row,0).toString());
                String addressPU = transportManager.pu_Table.getValueAt(row,1).toString();
                String contactNamePU = transportManager.pu_Table.getValueAt(row,2).toString();
                int peicesPU = Integer.parseInt(transportManager.pu_Table.getValueAt(row,3).toString());
                double totalWeightPU = Double.parseDouble(transportManager.pu_Table.getValueAt(row,4).toString());

                pickupTextArea.getLineWrap();
                pickupTextArea.setEditable(false);
                pickupTextArea.setLineWrap(true);

                pickupTextArea.append("Order number: " + orderNumPU + newLine);
                pickupTextArea.append("Address: " + addressPU + newLine);
                pickupTextArea.append("Contact name: " + contactNamePU + newLine);
                pickupTextArea.append("Pieces: " + peicesPU + newLine);
                pickupTextArea.append("Total weight: " + totalWeightPU + newLine);


                /*
                This is for the delivery table
                 */
                int ordernumDEL = Integer.parseInt(transportManager.delModel.getValueAt(row,0).toString());
                String addressDel = transportManager.delModel.getValueAt(row,1).toString();
                String contactName = transportManager.delModel.getValueAt(row,2).toString();
                int peices = Integer.parseInt(transportManager.delModel.getValueAt(row,3).toString());
                double totalWeight = Double.parseDouble(transportManager.delModel.getValueAt(row,4).toString());

                deliveryTextArea.getLineWrap();
                deliveryTextArea.setEditable(false);
                deliveryTextArea.setLineWrap(true);

                deliveryTextArea.append("Order number: " + ordernumDEL + newLine);
                deliveryTextArea.append("Address: " + addressDel + newLine);
                deliveryTextArea.append("Contact name: " + contactName + newLine);
                deliveryTextArea.append("Pieces: " + peices + newLine);
                deliveryTextArea.append("Total weight: " + totalWeight + newLine);




            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                driverDetails.this.dispose();


            }
        });

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
