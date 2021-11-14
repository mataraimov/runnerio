module com.example.runnerio {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.runnerio to javafx.fxml;
    exports com.example.runnerio;
}