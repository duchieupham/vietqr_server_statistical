package com.vietqr.org.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;

@Service
public class ScheduledTasks {
    private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);
    private final ReactiveUserService reactiveUserService;
    private final TrDateService trDateService;
    private final TrMonthService trMonthService;

    @Autowired
    public ScheduledTasks(ReactiveUserService reactiveUserService, TrDateService trDateService, TrMonthService trMonthService) {
        this.reactiveUserService = reactiveUserService;
        this.trDateService = trDateService;
        this.trMonthService = trMonthService;
    }

    // TAT CA DEU DE 30S DE DONG BO
    @Scheduled(cron = "0 * * * * ?")
    public void syncUserStatistics() {
        String yesterday = LocalDate.now().minusDays(1).toString();

        reactiveUserService.getRegisteredUsers(yesterday).subscribe(users -> {
            String sumUserJson = reactiveUserService.getSumUserJson();
            logger.info("Received " + users.size() + " users");
            logger.info("Sum User JSON: " + sumUserJson);
            long count = users.size();
            trDateService.saveUserStatistics(count, sumUserJson, yesterday);
        }, throwable -> logger.error("Failed to fetch registered users: " + throwable.getMessage()));
    }

    @Scheduled(cron = "0 * * * * ?")
    public void syncMonthlyUserStatistics() {
        YearMonth lastMonth = YearMonth.now().minusMonths(1);
        String lastMonthString = lastMonth.toString();

        reactiveUserService.getRegisteredUsersInMonth(lastMonthString).subscribe(users -> {
            String sumUserJson = reactiveUserService.getSumUserJson();
            logger.info("Received " + users.size() + " users");
            logger.info("Sum User JSON: " + sumUserJson);
            long count = users.size();
            trMonthService.saveUserStatistics(count, sumUserJson, lastMonthString);
        }, throwable -> logger.error("Failed to fetch registered users: " + throwable.getMessage()));
    }
}


