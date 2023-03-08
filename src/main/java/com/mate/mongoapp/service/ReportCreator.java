package com.mate.mongoapp.service;

import com.mate.mongoapp.model.CurrencyPair;
import com.mate.mongoapp.model.Pair;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportCreator {
    private static final String REPORT_FILE_NAME = "report.csv";
    private final PairService pairService;

    @Autowired
    public ReportCreator(PairService pairService) {
        this.pairService = pairService;
    }

    public File reportGenerator() throws IOException {
        StringBuilder reportBuilder = new StringBuilder();
        for (Pair pair : Pair.values()) {
            reportBuilder.append(pair.getName())
                    .append(" min_price ")
                    .append(priceGetter(pairService.getMinPrice(pair.getName())))
                    .append("; max_price ")
                    .append(priceGetter(pairService.getMaxPrice(pair.getName())))
                    .append(System.lineSeparator());
        }
        File reportFile = new File(REPORT_FILE_NAME);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(reportFile))) {
            bufferedWriter.write(reportBuilder.toString());
        } catch (IOException e) {
            throw new IOException("Can't write to file!", e);
        }
        return reportFile;
    }

    private BigDecimal priceGetter(CurrencyPair pair) {
        JSONObject jsonObject = new JSONObject(pair);
        return jsonObject.getBigDecimal("price");
    }
}
