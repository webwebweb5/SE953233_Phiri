package se233.chapter2currencyexchange.controller.draw;

import javafx.geometry.Insets;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;
import se233.chapter2currencyexchange.model.Currency;
import se233.chapter2currencyexchange.model.CurrencyEntity;

import java.util.concurrent.Callable;

public class DrawGraphTask implements Callable<VBox> {
    Currency currency;
    public DrawGraphTask(Currency currency){
        this.currency = currency;
    }
    @Override
    public VBox call() throws Exception{
        VBox graphPane = new VBox(10);
        graphPane.setPadding(new Insets(0,25,5,25));
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        yAxis.setAutoRanging(true);
        LineChart lineChart = new LineChart(xAxis,yAxis);
        lineChart.setLegendVisible(false);
        if(this.currency != null){
            XYChart.Series series = new XYChart.Series();
            double min_y = Double.MAX_VALUE;
            double max_y = Double.MIN_VALUE;
            for(CurrencyEntity c : currency.getHistorical()){
                series.getData().add(new XYChart.Data(c.getTimestamp(),c.getRate()));
                if(c.getRate() > max_y) max_y = c.getRate();
                if(c.getRate() < min_y) min_y = c.getRate();
            }
            yAxis.setAutoRanging(false);
            yAxis.setLowerBound(min_y-(max_y-min_y)/2);
            yAxis.setUpperBound(max_y+(max_y-min_y)/2);
            yAxis.setTickUnit((max_y-min_y)/2);
            lineChart.getData().add(series);
        }
        graphPane.getChildren().add(lineChart);
        return graphPane;
    }
}
