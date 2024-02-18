package com.pa;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ColorPicker extends Application {

    private Text text;
    private ScrollBar redSlider;
    private ScrollBar greenSlider;
    private ScrollBar blueSlider;
    private ScrollBar opacitySlider;

    @Override
    public void start(@SuppressWarnings("exports") Stage primaryStage) {
        text = new Text("Colorful Text");
        text.setFont(Font.font("Verdana", FontWeight.BOLD, 30));

        redSlider = createSlider("Red", 0, 255, 127);
        greenSlider = createSlider("Green", 0, 255, 127);
        blueSlider = createSlider("Blue", 0, 255, 127);
        opacitySlider = createSlider("Opacity", 0, 1, 1);

        VBox root = new VBox(10);
        root.setStyle("-fx-padding: 20px; -fx-background-color: #f0f0f0;");
        root.getChildren().addAll(text, redSlider, greenSlider, blueSlider, opacitySlider);

        Scene scene = new Scene(root, 400, 200);
        primaryStage.setTitle("Color Selector");
        primaryStage.setScene(scene);
        primaryStage.show();

        updateTextColor();

        redSlider.valueProperty().addListener((observable, oldValue, newValue) -> updateTextColor());
        greenSlider.valueProperty().addListener((observable, oldValue, newValue) -> updateTextColor());
        blueSlider.valueProperty().addListener((observable, oldValue, newValue) -> updateTextColor());
        opacitySlider.valueProperty().addListener((observable, oldValue, newValue) -> updateTextColor());
    }

    private ScrollBar createSlider(String name, double min, double max, double initialValue) {
        ScrollBar slider = new ScrollBar();
        slider.setMin(min);
        slider.setMax(max);
        slider.setValue(initialValue);
        slider.setUnitIncrement(1);

        Label label = new Label(name + ": ");
        label.setStyle("-fx-font-weight: bold;");

        slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            label.setText(name + ": " + String.format("%.0f", newValue.doubleValue()));
        });

        return slider;
    }

    private void updateTextColor() {
        double red = redSlider.getValue() / 255;
        double green = greenSlider.getValue() / 255;
        double blue = blueSlider.getValue() / 255;
        double opacity = opacitySlider.getValue();

        text.setFill(new Color(red, green, blue, opacity));
    }

    public static void main(String[] args) {
        launch(args);
    }
}