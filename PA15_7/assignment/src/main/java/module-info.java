module com.pa15_7 {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.pa15_7 to javafx.fxml;
    exports com.pa15_7;
}
