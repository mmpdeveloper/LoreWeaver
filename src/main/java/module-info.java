module com.example.loreweaverai {
    requires javafx.controls;
    requires javafx.fxml;
    requires jython.slim;


    opens com.example.loreweaverai to javafx.fxml;
    exports com.example.loreweaverai;
}