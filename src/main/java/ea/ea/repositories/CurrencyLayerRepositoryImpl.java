package ea.ea.repositories;

import com.google.gson.*;
import ea.ea.exceptions.InvalidDataException;
import ea.ea.models.ExchangeRate;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

/**
 * Repository for data from Currency Layer.
 */

@Component
public class CurrencyLayerRepositoryImpl implements CurrencyLayerRepository {

    private static final String US_DOLLAR_CURRENCY = "USD";

    @Override
    public double getExchangeRateData(String sourceCurrency, String targetCurrency) {
        ExchangeRate exchangeRate = new ExchangeRate(sourceCurrency, targetCurrency);
        try {
            String baseUrl =
                    "http://api.currencylayer.com/live?access_key=5c0fb24f33931ad3a08068bb650ec77c";
            URL urlForGetRequest = new URL(baseUrl);
            HttpURLConnection connection = getHttpURLConnection(urlForGetRequest);
            getConversionRate(exchangeRate, connection);
        } catch (IOException e) {
            throw new InvalidDataException("link");
        }
        return exchangeRate.getConversionRate();
    }

    private HttpURLConnection getHttpURLConnection(URL urlForGetRequest) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) urlForGetRequest.openConnection();
        connection.setRequestMethod("GET");
        return connection;
    }

    private void getConversionRate(ExchangeRate exchangeRate, HttpURLConnection connection) throws IOException {
        int responseCode = connection.getResponseCode();
        double convertionRate = 0;
        if (responseCode == HttpURLConnection.HTTP_OK) {
            String json = getJson(connection);
            JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
            Set<Map.Entry<String, JsonElement>> quotes = jsonObject
                    .getAsJsonObject("quotes")
                    .entrySet();
            String targetCurrency = exchangeRate.getTargetCurrency();
            String sourceCurrency = exchangeRate.getSourceCurrency();

            double convertionRateTC = 0;
            double convertionRateSC = 0;
            for (Map.Entry<String, JsonElement> entry : quotes) {

                if (entry.getKey().equals(US_DOLLAR_CURRENCY + sourceCurrency)) {
                    convertionRateSC = 1 / entry.getValue().getAsDouble();
                }
                if (entry.getKey().equals(US_DOLLAR_CURRENCY + targetCurrency)) {
                    convertionRateTC = entry.getValue().getAsDouble();
                }
                convertionRate = convertionRateSC * convertionRateTC;
            }
        }
        exchangeRate.setConversionRate(convertionRate);
    }

    private String getJson(HttpURLConnection connection) throws IOException {
        String readLine;
        BufferedReader in = new BufferedReader(
                new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        while ((readLine = in.readLine()) != null) {
            response.append(readLine);
        }
        in.close();
        return response.toString();
    }
}