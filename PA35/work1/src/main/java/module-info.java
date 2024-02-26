module com.work1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.work1 to javafx.fxml;
    exports com.work1;
}
