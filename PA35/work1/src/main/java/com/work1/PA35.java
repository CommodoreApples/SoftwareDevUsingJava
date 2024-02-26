package com.work1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

public class PA35 extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(@SuppressWarnings("exports") Stage primaryStage) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "@Mda91326ata@")) {
            // Create database
            try (Statement stmt = conn.createStatement()) {
                stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS MySQL80");
            }

            // Create user and grant privileges
            try (Statement stmt = conn.createStatement()) {
                stmt.executeUpdate("CREATE USER IF NOT EXISTS 'Scott'@'localhost' IDENTIFIED BY 'tiger'");
                stmt.executeUpdate("GRANT ALL PRIVILEGES ON MySQL80.* TO 'Scott'@'localhost'");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        DBConnectionPanel panel = new DBConnectionPanel();
        panel.addConnectListener(() -> {
            try {
                // Connect to the default MySQL database
                String url = "jdbc:mysql://localhost:3306/MySQL80";
                String username = panel.getUsername();
                String password = panel.getPassword();

                Connection conn = DriverManager.getConnection(url, username, password);

                // Perform database operations
                long startTime1 = System.currentTimeMillis();
                insertRecords(conn, false);
                long endTime1 = System.currentTimeMillis();

                long startTime2 = System.currentTimeMillis();
                insertRecords(conn, true);
                long endTime2 = System.currentTimeMillis();

                System.out.println("Time taken without batch updates: " + (endTime1 - startTime1) + " ms");
                System.out.println("Time taken with batch updates: " + (endTime2 - startTime2) + " ms");

                conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        Scene scene = new Scene(panel, 400, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Database Performance Comparison");
        primaryStage.show();
    }

    private static void insertRecords(Connection conn, boolean useBatchUpdates) throws SQLException {
        String sql = "INSERT INTO Temp(num1, num2, num3) VALUES (?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);

        Random random = new Random();

        for (int i = 0; i < 1000; i++) {
            double num1 = random.nextDouble();
            double num2 = random.nextDouble();
            double num3 = random.nextDouble();

            stmt.setDouble(1, num1);
            stmt.setDouble(2, num2);
            stmt.setDouble(3, num3);

            if (useBatchUpdates) {
                stmt.addBatch();
            } else {
                stmt.executeUpdate();
            }
        }

        if (useBatchUpdates) {
            stmt.executeBatch();
        }

        stmt.close();
    }
}
