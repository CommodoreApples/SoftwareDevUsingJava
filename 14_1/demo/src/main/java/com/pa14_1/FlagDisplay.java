package com.pa14_1;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class FlagDisplay extends Application {

    @Override
    public void start(@SuppressWarnings("exports") Stage primaryStage) {
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        // Add column constraints
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(25);
        gridPane.getColumnConstraints().addAll(col1, col1, col1, col1);

        // Add row constraints
        RowConstraints row1 = new RowConstraints();
        row1.setPercentHeight(50);
        gridPane.getRowConstraints().addAll(row1, row1);

        // Load and add images to the grid pane
        String[] imagePaths = {"flag1.gif", "flag2.gif", "flag6.gif", "flag7.gif"};
        int col = 0;
        int row = 0;
        for (String imagePath : imagePaths) {
            Image image = new Image(getClass().getResourceAsStream(imagePath));
            ImageView imageView = new ImageView(image);
            gridPane.add(imageView, col, row);

            col++;
            if (col > 3) {
                col = 0;
                row++;
            }
        }

        Scene scene = new Scene(gridPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Flag Display");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
