package se233.chapter2currencyexchange.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import se233.chapter2currencyexchange.controller.AllEventHandlers;
import se233.chapter2currencyexchange.controller.draw.DrawGraphTask;
import se233.chapter2currencyexchange.model.Currency;

import java.util.concurrent.*;

public class CurrencyPane extends BorderPane implements Callable<Void> {
    private Currency currency;
    private Button watch;
    private Button delete;
    private Button unWatch; // Exercise 3
    public CurrencyPane(Currency currency){
        this.watch = new Button("Watch");
        this.setPadding(new Insets(0));
        this.setPrefSize(640,300);
        this.setStyle("-fx-border-color: black");
        this.delete = new Button("Delete");
        this.unWatch = new Button("Unwatch");
        this.watch.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                AllEventHandlers.onWatch(currency.getShortCode());
            }
        });
        this.delete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                AllEventHandlers.onDelete(currency.getShortCode());
            }
        });
        this.unWatch.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                AllEventHandlers.onUnWatch(currency.getShortCode());
            }
        });

        this.setPadding(new Insets(0));
        this.setPrefSize(640,300);
        this.setStyle("-fx-background-color: white");
        try{
            this.refreshPane(currency);
        } catch (ExecutionException e){
            System.out.println("Encountered an execution exception.");
        } catch (InterruptedException e){
            System.out.println("Encountered an interrupted exception.");
        }
    }
    // Exercise ?
    @Override
    public Void call() throws Exception{
        System.out.println("hey");
        return null;
    }
    public void refreshPane(Currency currency) throws ExecutionException,InterruptedException {
        this.currency = currency;
        Pane currencyInfo = genInfoPane();
        FutureTask futureTask = new FutureTask<VBox>(new DrawGraphTask(currency));
        // Exercise 2???
//        FutureTask futureTask = new FutureTask<VBox>(new controller.draw.DrawGraphTask(currency));
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(futureTask);
        VBox currencyGraph = (VBox)futureTask.get();
        Pane topArea = genTopArea();
        this.setTop(topArea);
        this.setLeft(currencyInfo);
        this.setCenter(currencyGraph);
    }

    private Pane genInfoPane() {
        VBox currencyInfoPane = new VBox(10);
        currencyInfoPane.setPadding(new Insets(5,25,5,25));
        currencyInfoPane.setAlignment(Pos.CENTER);
        Label exchangeString = new Label("");
        Label watchString = new Label("");
        exchangeString.setStyle("-fx-font-size:20");
        watchString.setStyle("-fx-font-size:14");
        if(this.currency != null){
            exchangeString.setText(String.format("%s: %.4f",currency.getShortCode(),currency.getCurrent().getRate()));
            if(this.currency.getWatch()){
                watchString.setText(String.format("(Watch @%.4f)",currency.getWatchRate()));
            }
        }
        currencyInfoPane.getChildren().addAll(exchangeString,watchString);
        return currencyInfoPane;
    }
    private HBox genTopArea(){
        HBox topArea = new HBox(10);
        topArea.setPadding(new Insets(5));
        topArea.getChildren().addAll(watch,unWatch,delete);
        ((HBox)topArea).setAlignment(Pos.CENTER_RIGHT);
        return topArea;
    }
}
