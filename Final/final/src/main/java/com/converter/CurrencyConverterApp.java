package com.converter;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class CurrencyConverterApp extends Application {

    // Fixed currency conversion rates, 2/18/2024
    private static final double USD_TO_EUR = 0.93;
    private static final double USD_TO_GBP = 0.79;
    private static final double USD_TO_RUB = 92.00;
    private static final double USD_TO_JPY = 150.05;

    private ObservableList<String> conversionHistory = FXCollections.observableArrayList();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(@SuppressWarnings("exports") Stage primaryStage) {
        primaryStage.setTitle("Currency Converter");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        // ChoiceBox for selecting input currency
        ChoiceBox<String> inputCurrencyChoice = new ChoiceBox<>();
        inputCurrencyChoice.getItems().addAll("USD", "EUR", "GBP", "RUB", "JPY");
        inputCurrencyChoice.setValue("USD"); // Default value
        GridPane.setConstraints(inputCurrencyChoice, 0, 0);

        // Input field for amount
        TextField amountField = new TextField();
        GridPane.setConstraints(amountField, 1, 0);

        // ChoiceBox for selecting output currency
        ChoiceBox<String> outputCurrencyChoice = new ChoiceBox<>();
        outputCurrencyChoice.getItems().addAll("USD", "EUR", "GBP", "RUB", "JPY");
        outputCurrencyChoice.setValue("EUR"); // Default value
        GridPane.setConstraints(outputCurrencyChoice, 0, 1);

        // Display field for converted amount
        TextField convertedField = new TextField();
        convertedField.setEditable(false); // Read-only
        GridPane.setConstraints(convertedField, 1, 1);

        // Bind convertedField text to the conversion result
        convertedField.textProperty().bind(Bindings.createStringBinding(() -> {
            try {
                double amount = Double.parseDouble(amountField.getText());
                String inputCurrency = inputCurrencyChoice.getValue();
                String outputCurrency = outputCurrencyChoice.getValue();
                double convertedAmount = convert(amount, inputCurrency, outputCurrency);
                return String.format("%.2f %s", convertedAmount, outputCurrency);
            } catch (NumberFormatException ex) {
                return "Invalid input";
            }
        }, amountField.textProperty(), inputCurrencyChoice.valueProperty(), outputCurrencyChoice.valueProperty()));

        // Button to add conversion to history
        Button addButton = new Button("Add to History");
        addButton.setOnAction(e -> {
            String conversion = String.format("%s %s -> %s", amountField.getText(), inputCurrencyChoice.getValue(), convertedField.getText());
            conversionHistory.add(conversion);
        });
        GridPane.setConstraints(addButton, 1, 2);

        // ListView for displaying conversion history
        ListView<String> historyListView = new ListView<>(conversionHistory);
        historyListView.setPrefHeight(100);
        GridPane.setConstraints(historyListView, 0, 3, 2, 1);

        grid.getChildren().addAll(inputCurrencyChoice, amountField, outputCurrencyChoice, convertedField, addButton, historyListView);

        Scene scene = new Scene(grid, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private double convert(double amount, String inputCurrency, String outputCurrency) {
        double usdAmount;
        switch (inputCurrency) {
            case "USD":
                usdAmount = amount;
                break;
            case "EUR":
                usdAmount = amount / USD_TO_EUR;
                break;
            case "GBP":
                usdAmount = amount / USD_TO_GBP;
                break;
            case "RUB":
                usdAmount = amount / USD_TO_RUB;
                break;
            case "JPY":
                usdAmount = amount / USD_TO_JPY;
                break;
            default:
                return 0.0;
        }

        switch (outputCurrency) {
            case "USD":
                return usdAmount;
            case "EUR":
                return usdAmount * USD_TO_EUR;
            case "GBP":
                return usdAmount * USD_TO_GBP;
            case "RUB":
                return usdAmount * USD_TO_RUB;
            case "JPY":
                return usdAmount * USD_TO_JPY;
            default:
                return 0.0;
        }
    }
}