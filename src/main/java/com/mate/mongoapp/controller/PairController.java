package com.mate.mongoapp.controller;

import com.mate.mongoapp.model.CurrencyPair;
import com.mate.mongoapp.model.Pair;
import com.mate.mongoapp.model.mapper.PairMapper;
import com.mate.mongoapp.service.PairService;
import com.mate.mongoapp.service.ReportCreator;
import com.mate.mongoapp.utils.AppConstants;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cryptocurrencies")
public class PairController {
    private static final String ERROR_MESSAGE = "ERROR";
    private final PairService pairService;
    private final PairMapper pairMapper;
    private final ReportCreator reportCreator;

    public PairController(PairService pairService, PairMapper pairMapper,
                          ReportCreator reportCreator) {
        this.pairService = pairService;
        this.pairMapper = pairMapper;
        this.reportCreator = reportCreator;
    }

    @GetMapping("/minprice")
    public ResponseEntity<?> getMinPairPrice(@RequestParam(value = "name")
                                                 String name) {
        if (pairRecognize(name).equals(ERROR_MESSAGE)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Currency pair name is incorrect!");
        }
        CurrencyPair minPricePair = pairService.getMinPrice(pairRecognize(name));
        return ResponseEntity.ok(pairMapper.mapToto(minPricePair));
    }

    @GetMapping("/maxprice")
    public ResponseEntity<?> getMaxPairPrice(@RequestParam(value = "name")
                                                 String name) {
        if (pairRecognize(name).equals(ERROR_MESSAGE)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Currency pair name is incorrect!");
        }
        CurrencyPair maxPricePair = pairService.getMaxPrice(pairRecognize(name));
        return ResponseEntity.ok(pairMapper.mapToto(maxPricePair));
    }

    @GetMapping
    public ResponseEntity<?> getSortedPage(
            @RequestParam(
                    value = "name") String name,
            @RequestParam(
                    value = "page",
                    defaultValue = AppConstants.DEFAULT_PAGE_NUMBER,
                    required = false) int number,
            @RequestParam(
                    value = "size",
                    defaultValue = AppConstants.DEFAULT_PAGE_SIZE_ELEMENT,
                    required = false) int size) {
        if (pairRecognize(name).equals(ERROR_MESSAGE)
                || size < 0 || number < 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Request parameters are incorrect!");
        }
        return ResponseEntity.ok(pairService.getAll(pairRecognize(name), number,
                 size));
    }

    @GetMapping("/csv")
    public void getReport(HttpServletRequest request,
                          HttpServletResponse response) throws IOException {
        File report = reportCreator.reportGenerator();
        String mimeType = URLConnection
                .guessContentTypeFromName(report.getName());
        if (mimeType == null) {
            mimeType = "application/octet-stream";
        }
        response.setContentType(mimeType);
        response.setHeader("Content-Disposition", String
                .format("inline; filename=\"" + report.getName() + "\""));
        response.setContentLength((int) report.length());
        InputStream inputStream = new BufferedInputStream(new FileInputStream(report));
        FileCopyUtils.copy(inputStream, response.getOutputStream());
    }

    private String pairRecognize(String name) {
        Pair[] pairs = Pair.values();
        for (Pair pair : pairs) {
            if (pair.getName().toLowerCase().contains(name)) {
                return pair.getName();
            }
        }
        return ERROR_MESSAGE;
    }
}
