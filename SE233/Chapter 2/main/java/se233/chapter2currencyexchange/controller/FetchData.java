package se233.chapter2currencyexchange.controller;

import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import se233.chapter2currencyexchange.model.CurrencyEntity;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

import org.json.JSONObject;

public class FetchData {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static ArrayList<CurrencyEntity> fetch_range(String src, int N) throws JSONException {
        String dateEnd = LocalDate.now().format(formatter);
        String dateStart = LocalDate.now().minusDays(N).format(formatter);
        String apiKey = "22b380a4506fa02f0348"; //22b380a4506fa02f0348 //4544bfb65a40abc77e92
        String url_str = String.format("https://free.currconv.com/api/v7/convert?q=%s_THB&compact=ultra&date=%s&endDate=%s&apiKey=%s", src, dateStart, dateEnd, apiKey);
        ArrayList<CurrencyEntity> histList = new ArrayList<>();
        String retrievedJson = null;
        try {
            retrievedJson = IOUtils.toString(new URL(url_str), Charset.defaultCharset());
        } catch (MalformedURLException e) {
            System.out.println("Encountered a Malformed Url exception");
        } catch (IOException e) {
            System.out.println("Encounter an IO exception");
        }
        JSONObject jsonOBJ = new JSONObject(retrievedJson).getJSONObject(String.format("%s_THB", src));
        Iterator keysToCopyIterator = jsonOBJ.keys();
        while (keysToCopyIterator.hasNext()) {
            String key = (String) keysToCopyIterator.next();
            Double rate = Double.parseDouble(jsonOBJ.get(key).toString());
            histList.add(new CurrencyEntity(rate, key));
        }
        histList.sort(new Comparator<CurrencyEntity>() {
            @Override
            public int compare(CurrencyEntity o1, CurrencyEntity o2) {
                return o1.getTimestamp().compareTo(o2.getTimestamp());
            }
        });

        return histList;
    }
}
