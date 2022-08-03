package se233.chapter2currencyexchange.model;

public class CurrencyEntity {
    private double rate;
    private String date;

    public CurrencyEntity(double rate,String date){
        this.rate = rate;
        this.date = date;
    }

    public double getRate() {
        return rate;
    }

    public String getTimestamp(){
        return date;
    }

    public String toString(){
        return String.format("%s %.4f",date,rate);
    }
}
