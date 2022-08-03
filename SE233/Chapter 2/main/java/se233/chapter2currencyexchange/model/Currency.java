package se233.chapter2currencyexchange.model;

import java.util.ArrayList;

public class Currency {
    private String shortCode;
    private CurrencyEntity current;
    private ArrayList<CurrencyEntity> historical;
    private boolean isWatch;
    private double watchRate;
    public Currency(String shortCode){
        this.shortCode = shortCode;
        this.isWatch = false;
        this.watchRate = 0.0;
    }

    public String getShortCode() {
        return shortCode;
    }

    public CurrencyEntity getCurrent() {
        return current;
    }

    public double getWatchRate() {
        return watchRate;
    }

    public void setWatchRate(double watchRate) {
        this.watchRate = watchRate;
    }

    public boolean getWatch() {
        return isWatch;
    }

    public void setWatch(boolean watch) {
        isWatch = watch;
    }

    public void setHistorical(ArrayList<CurrencyEntity> historical) {
        this.historical = historical;
    }

    public ArrayList<CurrencyEntity> getHistorical() {
        return historical;
    }

    public void setCurrent(CurrencyEntity current) {
        this.current = current;
    }

}
