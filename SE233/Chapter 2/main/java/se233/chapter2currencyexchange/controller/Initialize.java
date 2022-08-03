package se233.chapter2currencyexchange.controller;

import org.json.JSONException;
import se233.chapter2currencyexchange.Launcher;
import se233.chapter2currencyexchange.model.Currency;
import se233.chapter2currencyexchange.model.CurrencyEntity;

import java.util.ArrayList;

public class Initialize {
    public static void initialize_app() throws JSONException {
        Currency c = new Currency("USD");
        // change 8 to 6
        ArrayList<CurrencyEntity> c_list = FetchData.fetch_range(c.getShortCode(),6);
        c.setHistorical(c_list);
        c.setCurrent(c_list.get(c_list.size()-1));
        ArrayList<Currency> currencyList = new ArrayList<>();
        currencyList.add(c);
        Launcher.setCurrency(currencyList);
    }
}
