module com.pa14_1 {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.pa14_1 to javafx.fxml;
    exports com.pa14_1;
}
