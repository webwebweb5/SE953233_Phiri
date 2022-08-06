module se233.chapter3reverseindexcreation {
    requires javafx.controls;
    requires javafx.fxml;
    requires pdfbox.app;


    opens se233.chapter3reverseindexcreation to javafx.fxml;
    opens se233.chapter3reverseindexcreation.controller to javafx.fxml;
    exports se233.chapter3reverseindexcreation;
    exports se233.chapter3reverseindexcreation.controller;
}