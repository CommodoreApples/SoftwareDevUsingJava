module com.work2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.work2 to javafx.fxml;
    exports com.work2;
}
