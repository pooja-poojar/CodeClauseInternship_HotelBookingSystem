package com.codeclause.project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;



public class HotelBookingSystem extends JFrame {

    private JTextField roomNumberField;
    private JTextField nameOfPersonField;
    private JTextField numberOfPeopleField;
    private JTextField paymentField;
    private JTextField checkInDateField;
    private JTextField checkOutDateField;

    public HotelBookingSystem() {
        // Initialize your UI components
        JLabel roomNumberLabel = new JLabel("Room Number:");
        roomNumberField = new JTextField(10);
        
        JLabel nameLabel = new JLabel("Name of Person:");
        nameOfPersonField = new JTextField(10);

        JLabel numberOfPeopleLabel = new JLabel("Number of People:");
        numberOfPeopleField = new JTextField(10);

        JLabel paymentLabel = new JLabel("Payment:");
        paymentField = new JTextField(10);

        JLabel checkInLabel = new JLabel("Check-In Date:");
        checkInDateField = new JTextField(10);

        JLabel checkOutLabel = new JLabel("Check-Out Date:");
        checkOutDateField = new JTextField(10);

        JButton reserveButton = new JButton("Reserve Room");
        reserveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reserveRoom();
            }
        });

        // Add components to the frame
        setLayout(new GridLayout(7, 2));
        add(roomNumberLabel);
        add(roomNumberField);
        add(nameLabel);
        add(nameOfPersonField);
        add(numberOfPeopleLabel);
        add(numberOfPeopleField);
        add(paymentLabel);
        add(paymentField);

        add(checkInLabel);
        add(checkInDateField);
        add(checkOutLabel);
        add(checkOutDateField);
        add(reserveButton);

        // Set frame properties
        setTitle("Hotel Booking System");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void reserveRoom() {
        String roomNumber = roomNumberField.getText();
        String name = nameOfPersonField.getText();
        String numberOfPeople = numberOfPeopleField.getText();
        String payment = paymentField.getText();
        String checkInDate = checkInDateField.getText();
        String checkOutDate = checkOutDateField.getText();

        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel", "root", "root");
            String query = "INSERT INTO reservationsroom (room_number,name, number_of_people, payment, check_in_date, check_out_date) VALUES (?, ?, ?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, roomNumber);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, numberOfPeople);
            preparedStatement.setString(4, payment);
            preparedStatement.setString(5, checkInDate);
            preparedStatement.setString(6, checkOutDate);

            preparedStatement.executeUpdate();

            JOptionPane.showMessageDialog(null, "Room Reserved!");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Reservation failed. Please try again.");
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }


  
	public static void main(String[] args)  {  
		 SwingUtilities.invokeLater(new Runnable() {
	            @Override
	            public void run() {
	                new HotelBookingSystem();
	            }

		 } );

	}  
}  


