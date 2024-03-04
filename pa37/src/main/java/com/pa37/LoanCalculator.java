package com.pa37;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class LoanCalculator extends Application {

    @Override
    public void start(@SuppressWarnings("exports") Stage primaryStage) {
        //Creates a GridPane for the layout
        GridPane gridPane = new GridPane();
        gridPane.setHgap(5);
        gridPane.setVgap(5);

        //Adds the Taxable Income label and TextField
        gridPane.add(new Label("Taxable Income:"), 0, 0);
        TextField tfTaxableIncome = new TextField();
        gridPane.add(tfTaxableIncome, 1, 0);

        //Adds the Filing Status label and ComboBox
        gridPane.add(new Label("Filing Status:"), 0, 1);
        ComboBox<String> cbFilingStatus = new ComboBox<>();
        cbFilingStatus.getItems().addAll("Single", "Married Filing Jointly", "Head of Household");
        cbFilingStatus.setValue("Single");
        gridPane.add(cbFilingStatus, 1, 1);

        //Adds the Compute Tax button
        Button btnCompute = new Button("Compute Tax");
        gridPane.add(btnCompute, 1, 2);

        //Sets an action for the Compute Tax button
        btnCompute.setOnAction(e -> {
            double taxableIncome = Double.parseDouble(tfTaxableIncome.getText());
            String filingStatus = cbFilingStatus.getValue();

            double tax = calculateTax(taxableIncome, filingStatus);
            showResult(taxableIncome, filingStatus, tax);
        });

        //Create a scene and set it to the stage
        Scene scene = new Scene(gridPane, 400, 200);
        primaryStage.setTitle("Tax Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    //Method that displays the tax result
    private void showResult(double taxableIncome, String filingStatus, double tax) {
        Stage stage = new Stage();
        GridPane resultPane = new GridPane();
        resultPane.setHgap(5);
        resultPane.setVgap(5);

        resultPane.add(new Label("Taxable Income:"), 0, 0);
        resultPane.add(new Label(Double.toString(taxableIncome)), 1, 0);

        resultPane.add(new Label("Filing Status:"), 0, 1);
        resultPane.add(new Label(filingStatus), 1, 1);

        resultPane.add(new Label("Tax Amount:"), 0, 2);
        resultPane.add(new Label(String.format("%.2f", tax)), 1, 2);

        Scene resultScene = new Scene(resultPane, 300, 150);
        stage.setTitle("Tax Calculation Result");
        stage.setScene(resultScene);
        stage.show();
    }

    //Method to calculate the tax based on taxable income and filing status
    private double calculateTax(double taxableIncome, String filingStatus) {
        double tax = 0;
        double[] brackets;
        double[] rates;

        //Define tax brackets and rates based on filing status
        if (filingStatus.equals("Single")) {
            brackets = new double[]{0, 9950, 40525, 86375, 164925, 209425, 523600};
            rates = new double[]{0.10, 0.12, 0.22, 0.24, 0.32, 0.35, 0.37};
        } else if (filingStatus.equals("Married Filing Jointly")) {
            brackets = new double[]{0, 19900, 81050, 172750, 329850, 418850, 628300};
            rates = new double[]{0.10, 0.12, 0.22, 0.24, 0.32, 0.35, 0.37};
        } else if (filingStatus.equals("Head of Household")) {
            brackets = new double[]{0, 14200, 54200, 86350, 164900, 209400, 523600};
            rates = new double[]{0.10, 0.12, 0.22, 0.24, 0.32, 0.35, 0.37};
        } else {
            throw new IllegalArgumentException("Invalid filing status");
        }

        //Calculate tax based on tax brackets and rates using of 2022
        int i = 0;
        while (taxableIncome > brackets[i]) {
            tax += (brackets[i + 1] - brackets[i]) * rates[i];
            i++;
        }
        tax += (taxableIncome - brackets[i]) * rates[i];

        return tax;
    }

    //Main method to launch the application
    public static void main(String[] args) {
        launch(args);
    }
}