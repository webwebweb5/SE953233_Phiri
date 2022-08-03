module se233.chapter2currencyexchange {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.json;
    requires org.apache.commons.io;

    opens se233.chapter2currencyexchange to javafx.fxml;
    exports se233.chapter2currencyexchange;
}