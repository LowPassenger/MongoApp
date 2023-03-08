package com.mate.mongoapp.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Log4j2
@Getter
@Service
@NoArgsConstructor
public class PriceGetter {
    private static final String commonUrl = "https://cex.io/api/last_price/";

    public BigDecimal pairPriceGetter(String pairName) {
        String inputLine;
        int status = 0;
        try {
            URL url = new URL(commonUrl + pairName);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            status = connection.getResponseCode();
            BufferedReader input = new BufferedReader(new InputStreamReader(connection
                    .getInputStream()));
            inputLine = input.readLine();
            input.close();
            connection.disconnect();
        } catch (IOException e) {
            log.info("URL link {} is not correct, HTTP status is {}", commonUrl, status);
            throw new RuntimeException("URL link " + commonUrl + pairName
                    + "is not correct, HTTP status is" + status, e);
        }
        JSONObject jsonObject = new JSONObject(inputLine);
        String price = jsonObject.getString("lprice");
        return BigDecimal.valueOf(Double.parseDouble(price));
    }
}
