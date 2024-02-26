package com.pa34_1;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Main extends Frame implements ActionListener {
    private TextField idField, lastNameField, firstNameField, miField, addressField, cityField, stateField, telephoneField, emailField;
    private Button viewButton, insertButton, updateButton, clearButton;
    private TextArea displayArea;

    private Connection connection;
    private Statement statement;

    public Main() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new Label("ID"), gbc);
        idField = new TextField(10);
        gbc.gridx = 1;
        add(idField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new Label("Last Name, First Name, MI"), gbc);
        Panel namePanel = new Panel();
        lastNameField = new TextField(15);
        namePanel.add(lastNameField);
        firstNameField = new TextField(15);
        namePanel.add(firstNameField);
        miField = new TextField(1);
        namePanel.add(miField);
        gbc.gridx = 1;
        add(namePanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new Label("Address"), gbc);
        addressField = new TextField(20);
        gbc.gridx = 1;
        add(addressField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(new Label("City, State"), gbc);
        Panel cityStatePanel = new Panel();
        cityField = new TextField(15);
        cityStatePanel.add(cityField);
        stateField = new TextField(2);
        cityStatePanel.add(stateField);
        gbc.gridx = 1;
        add(cityStatePanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        add(new Label("Telephone"), gbc);
        telephoneField = new TextField(10);
        gbc.gridx = 1;
        add(telephoneField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        add(new Label("Email"), gbc);
        emailField = new TextField(40);
        gbc.gridx = 1;
        add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        Panel buttonPanel = new Panel();
        viewButton = new Button("View");
        viewButton.addActionListener(this);
        buttonPanel.add(viewButton);
        insertButton = new Button("Insert");
        insertButton.addActionListener(this);
        buttonPanel.add(insertButton);
        updateButton = new Button("Update");
        updateButton.addActionListener(this);
        buttonPanel.add(updateButton);
        clearButton = new Button("Clear");
        clearButton.addActionListener(e -> clearRecord());
        buttonPanel.add(clearButton);

        add(buttonPanel, gbc);

        gbc.gridy = 7;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        displayArea = new TextArea(10, 60);
        add(displayArea, gbc);

        setTitle("Staff Information");
        pack();
        setVisible(true);

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/your_database_name", "username", "password");
            statement = connection.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == viewButton) {
            viewRecord();
        } else if (e.getSource() == insertButton) {
            insertRecord();
        } else if (e.getSource() == updateButton) {
            updateRecord();
        }
    }

    private void viewRecord() {
        String id = idField.getText();
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Staff WHERE id = '" + id + "'");
            if (resultSet.next()) {
                displayArea.setText(resultSet.getString("id") + ", " +
                        resultSet.getString("lastName") + ", " +
                        resultSet.getString("firstName") + ", " +
                        resultSet.getString("mi") + ", " +
                        resultSet.getString("address") + ", " +
                        resultSet.getString("city") + ", " +
                        resultSet.getString("state") + ", " +
                        resultSet.getString("telephone") + ", " +
                        resultSet.getString("email"));
            } else {
                displayArea.setText("Record not found.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void insertRecord() {
        String id = idField.getText();
        String lastName = lastNameField.getText();
        String firstName = firstNameField.getText();
        String mi = miField.getText();
        String address = addressField.getText();
        String city = cityField.getText();
        String state = stateField.getText();
        String telephone = telephoneField.getText();
        String email = emailField.getText();
        try {
            statement.executeUpdate("INSERT INTO Staff VALUES ('" +
                    id + "', '" +
                    lastName + "', '" +
                    firstName + "', '" +
                    mi + "', '" +
                    address + "', '" +
                    city + "', '" +
                    state + "', '" +
                    telephone + "', '" +
                    email + "')");
            displayArea.setText("Record inserted.");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void updateRecord() {
        String id = idField.getText();
        String lastName = lastNameField.getText();
        String firstName = firstNameField.getText();
        String mi = miField.getText();
        String address = addressField.getText();
        String city = cityField.getText();
        String state = stateField.getText();
        String telephone = telephoneField.getText();
        String email = emailField.getText();
        try {
            int rowsUpdated = statement.executeUpdate("UPDATE Staff SET " +
                    "lastName = '" + lastName + "', " +
                    "firstName = '" + firstName + "', " +
                    "mi = '" + mi + "', " +
                    "address = '" + address + "', " +
                    "city = '" + city + "', " +
                    "state = '" + state + "', " +
                    "telephone = '" + telephone + "', " +
                    "email = '" + email + "' " +
                    "WHERE id = '" + id + "'");
            if (rowsUpdated > 0) {
                displayArea.setText("Record updated.");
            } else {
                displayArea.setText("Record not found.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void clearRecord() {
        idField.setText("");
        lastNameField.setText("");
        firstNameField.setText("");
        miField.setText("");
        addressField.setText("");
        cityField.setText("");
        stateField.setText("");
        telephoneField.setText("");
        emailField.setText("");
        displayArea.setText("");
    }

    public static void main(String[] args) {
        new Main();
    }
}