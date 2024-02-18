module com.pa {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.pa to javafx.fxml;
    exports com.pa;
}
