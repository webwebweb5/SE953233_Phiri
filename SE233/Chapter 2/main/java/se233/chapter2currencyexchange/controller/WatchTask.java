package se233.chapter2currencyexchange.controller;


import javafx.scene.control.Alert;
import se233.chapter2currencyexchange.Launcher;
import se233.chapter2currencyexchange.model.Currency;

import java.util.ArrayList;
import java.util.concurrent.Callable;

public class WatchTask implements Callable<Void> {
    @Override
    public Void call() {
        ArrayList<Currency> allCurrency = Launcher.getCurrency();
        String found = "";
        for (int i = 0; i < allCurrency.size(); i++) {
            // < -> >
            if (allCurrency.get(i).getWatchRate() != 0 && allCurrency.get(i).getWatchRate() > allCurrency.get(i).getCurrent().getRate()) {
                if (found.equals("")) {
                    found = allCurrency.get(i).getShortCode();
                } else {
                    found = found + " and " + allCurrency.get(i).getShortCode();
                }
            }
        }
        if (!found.equals("")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle(null);
            alert.setHeaderText(null);
            if (found.length() > 3) {
                alert.setContentText(String.format("%s have become lower than the watch rate!", found));
            } else {
                alert.setContentText(String.format("%s has become higher than the watch rates!", found));
                alert.showAndWait();
            }
        }
        return null;
    }
}
