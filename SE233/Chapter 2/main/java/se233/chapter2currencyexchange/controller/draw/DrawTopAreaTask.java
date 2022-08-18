package se233.chapter2currencyexchange.controller.draw;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import se233.chapter2currencyexchange.model.Currency;
import se233.chapter2currencyexchange.view.CurrencyPane;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

public class DrawTopAreaTask implements Callable<HBox> {
    Currency currency;
    CurrencyPane currencyPane;
    Button watch;
    Button unwatch;
    Button delete;
    public DrawTopAreaTask(Currency currency, Button watch, Button unwatch, Button delete){
        this.currency = currency;
        this.watch = watch;
        this.unwatch = unwatch;
        this.delete = delete;
    }
    @Override
    public HBox call() throws ExecutionException, InterruptedException {
        HBox topArea = new HBox(10);
        topArea.setPadding(new Insets(5));
        topArea.getChildren().addAll(watch, unwatch, delete);
        ((HBox) topArea).setAlignment(Pos.CENTER_RIGHT);
        return topArea;
    }
    // set button
}
