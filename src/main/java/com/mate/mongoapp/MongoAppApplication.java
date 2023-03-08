package com.mate.mongoapp;

import com.mate.mongoapp.service.ParseThread;
import java.util.Timer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MongoAppApplication {
    private static final long TIME_RANGE = 10000;
    private static final long TIME_DELAY = 0;
    private static ParseThread parseThread;

    @Autowired
    public MongoAppApplication(ParseThread parseThread) {
        MongoAppApplication.parseThread = parseThread;
    }

    public static void main(String[] args) {
        SpringApplication.run(MongoAppApplication.class, args);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(parseThread, TIME_DELAY, TIME_RANGE);
    }
}
