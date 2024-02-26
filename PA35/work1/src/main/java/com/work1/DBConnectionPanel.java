package com.work1;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class DBConnectionPanel extends GridPane {

    private TextField urlField;
    private TextField usernameField;
    private PasswordField passwordField;
    private Button connectButton;
    private Runnable connectListener;

    public DBConnectionPanel() {
        initComponents();
        layoutComponents();
    }

    private void initComponents() {
        urlField = new TextField();
        urlField.setPromptText("Database URL");

        usernameField = new TextField();
        usernameField.setPromptText("Username");

        passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        connectButton = new Button("Connect to Database");
        connectButton.setOnAction(e -> {
            if (connectListener != null) {
                connectListener.run();
            }
        });
    }

    private void layoutComponents() {
        setPadding(new Insets(10));
        setHgap(10);
        setVgap(10);

        add(new Label("Database URL:"), 0, 0);
        add(urlField, 1, 0);

        add(new Label("Username:"), 0, 1);
        add(usernameField, 1, 1);

        add(new Label("Password:"), 0, 2);
        add(passwordField, 1, 2);

        add(connectButton, 0, 3, 2, 1);
    }

    public String getUrl() {
        return urlField.getText();
    }

    public String getUsername() {
        return usernameField.getText();
    }

    public String getPassword() {
        return passwordField.getText();
    }

    public void addConnectListener(Runnable listener) {
        this.connectListener = listener;
    }
}