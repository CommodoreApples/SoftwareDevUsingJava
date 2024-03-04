module com.pa37 {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.pa37 to javafx.fxml;
    exports com.pa37;
}
