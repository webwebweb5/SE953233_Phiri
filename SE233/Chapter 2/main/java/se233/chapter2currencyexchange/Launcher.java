package se233.chapter2currencyexchange;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import org.json.JSONException;
import se233.chapter2currencyexchange.controller.Initialize;
import se233.chapter2currencyexchange.controller.RefreshTask;
import se233.chapter2currencyexchange.model.Currency;
import se233.chapter2currencyexchange.view.CurrencyPane;
import se233.chapter2currencyexchange.view.CurrencyParentPane;
import se233.chapter2currencyexchange.view.TopPane;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class Launcher extends Application {
    private static Stage primaryStage;
    private static Scene mainScene;
    private static FlowPane mainPane;
    private static TopPane topPane;
    private static CurrencyParentPane currencyParentPane;
    private static ArrayList<Currency> currencyList;
    @Override
    public void start(Stage primaryStage) throws ExecutionException, InterruptedException, JSONException {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Currency Watcher");
        this.primaryStage.setResizable(false);
        Initialize.initialize_app();
        initMainPane();
        mainScene = new Scene(mainPane);
        this.primaryStage.setScene(mainScene);
        this.primaryStage.show();
        RefreshTask r = new RefreshTask();
        Thread th = new Thread(r);
        th.setDaemon(true);
        th.start();
    }

    public static void setCurrency(ArrayList<Currency> currency) {
        Launcher.currencyList = currency;
    }

    public static ArrayList<Currency> getCurrency() {
        return currencyList;
    }

    public void initMainPane() throws ExecutionException, InterruptedException {
        mainPane = new FlowPane();
        topPane = new TopPane();
        currencyParentPane = new CurrencyParentPane(this.currencyList);
        mainPane.getChildren().add(topPane);
        mainPane.getChildren().add(currencyParentPane);
    }

    public static void refreshPane() throws ExecutionException, InterruptedException {
        topPane.refreshPane();
        currencyParentPane.refreshPane(currencyList);
        primaryStage.sizeToScene();
    }

    public static void main(String[] args){
        launch(args);
    }
}
