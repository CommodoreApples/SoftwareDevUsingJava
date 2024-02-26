package com.work2;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class App extends Application {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/MySQL80";
    private static final String DB_USER = "alexa";
    private static final String DB_PASSWORD = "1234";

    @Override
    public void start(@SuppressWarnings("exports") Stage primaryStage) {
        primaryStage.setTitle("Staff Information");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);

        // Labels and text fields
        Label idLabel = new Label("ID:");
        grid.add(idLabel, 0, 0);
        TextField idField = new TextField();
        grid.add(idField, 1, 0);

        Label lastNameLabel = new Label("Last Name:");
        grid.add(lastNameLabel, 0, 1);
        TextField lastNameField = new TextField();
        grid.add(lastNameField, 1, 1);

        Label firstNameLabel = new Label("First Name:");
        grid.add(firstNameLabel, 2, 1);
        TextField firstNameField = new TextField();
        grid.add(firstNameField, 3, 1);

        Label miLabel = new Label("MI:");
        grid.add(miLabel, 4, 1);
        TextField miField = new TextField();
        grid.add(miField, 5, 1);

        Label addressLabel = new Label("Address:");
        grid.add(addressLabel, 0, 2);
        TextField addressField = new TextField();
        grid.add(addressField, 1, 2);

        Label cityLabel = new Label("City:");
        grid.add(cityLabel, 2, 2);
        TextField cityField = new TextField();
        grid.add(cityField, 3, 2);

        Label stateLabel = new Label("State:");
        grid.add(stateLabel, 4, 2);
        TextField stateField = new TextField();
        grid.add(stateField, 5, 2);

        Label telephoneLabel = new Label("Telephone:");
        grid.add(telephoneLabel, 0, 3);
        TextField telephoneField = new TextField();
        grid.add(telephoneField, 1, 3);

        Label emailLabel = new Label("Email:");
        grid.add(emailLabel, 0, 4);
        TextField emailField = new TextField();
        grid.add(emailField, 1, 4);

        // Buttons
        Button viewButton = new Button("View");
        grid.add(viewButton, 0, 9);

        Button insertButton = new Button("Insert");
        insertButton.setOnAction(e -> insertRecord(idField.getText(), lastNameField.getText(),
                firstNameField.getText(), miField.getText(), addressField.getText(),
                cityField.getText(), stateField.getText(), telephoneField.getText(),
                emailField.getText()));
        grid.add(insertButton, 1, 9);

        Button updateButton = new Button("Update");
        updateButton.setOnAction(e -> updateRecord(idField.getText(), lastNameField.getText(),
                firstNameField.getText(), miField.getText(), addressField.getText(),
                cityField.getText(), stateField.getText(), telephoneField.getText(),
                emailField.getText()));
        grid.add(updateButton, 2, 9);

        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> deleteRecord(idField.getText()));
        grid.add(deleteButton, 3, 9);

        // Create the scene
        Scene scene = new Scene(grid, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void insertRecord(String id, String lastName, String firstName, String mi,
                              String address, String city, String state, String telephone, String email) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "INSERT INTO Staff (id, lastName, firstName, mi, address, city, state, telephone, email) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, id);
            statement.setString(2, lastName);
            statement.setString(3, firstName);
            statement.setString(4, mi);
            statement.setString(5, address);
            statement.setString(6, city);
            statement.setString(7, state);
            statement.setString(8, telephone);
            statement.setString(9, email);
            statement.executeUpdate();
            System.out.println("Record inserted successfully.");
        } catch (SQLException ex) {
            System.err.println("Error inserting record: " + ex.getMessage());
        }
    }

    private void updateRecord(String id, String lastName, String firstName, String mi,
                              String address, String city, String state, String telephone, String email) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "UPDATE Staff SET lastName = ?, firstName = ?, mi = ?, address = ?, city = ?, state = ?, telephone = ?, email = ? WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, lastName);
            statement.setString(2, firstName);
            statement.setString(3, mi);
            statement.setString(4, address);
            statement.setString(5, city);
            statement.setString(6, state);
            statement.setString(7, telephone);
            statement.setString(8, email);
            statement.setString(9, id);
            statement.executeUpdate();
            System.out.println("Record updated successfully.");
        } catch (SQLException ex) {
            System.err.println("Error updating record: " + ex.getMessage());
        }
    }

    private void deleteRecord(String id) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "DELETE FROM Staff WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, id);
            statement.executeUpdate();
            System.out.println("Record deleted successfully.");
        } catch (SQLException ex) {
            System.err.println("Error deleting record: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}