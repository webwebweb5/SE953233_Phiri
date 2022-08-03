module com.example.advproassignment1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.advprogramassignment1 to javafx.fxml;
    exports com.example.advprogramassignment1;
}