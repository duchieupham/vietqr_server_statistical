package com.vietqr.org.grpc.scheduled;

import com.vietqr.org.grpc.reactive.ReactiveUserService;
import com.vietqr.org.service.TrDateService;
import com.vietqr.org.service.TrMonthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;

@Service
public class UserScheduledTasks {
    private static final Logger logger = LoggerFactory.getLogger(UserScheduledTasks.class);
    @Autowired
    private ReactiveUserService reactiveUserService;
    @Autowired
    private  TrDateService trDateService;
    @Autowired
    private  TrMonthService trMonthService;


    //@Scheduled(cron = "0 * * * * ?")
    @Scheduled(cron = "0 0 0 * * ?")
    public void syncDailyUserStatistics() {
        String yesterday = LocalDate.now().minusDays(1).toString();

        reactiveUserService.getRegisteredUsers(yesterday).subscribe(users -> {
            String sumUserJson = reactiveUserService.getSumUserJson();
            logger.info("Received " + users.size() + " users");
            logger.info("Sum User JSON: " + sumUserJson);
            long count = users.size();
            trDateService.saveUserStatistics(count, sumUserJson, yesterday);
        }, throwable -> logger.error("Failed to fetch registered users: " + throwable.getMessage()));
    }

    //@Scheduled(cron = "0 * * * * ?")
   // @Scheduled(cron = "0 0 0 1 * ?") // Chạy vào lúc 00:00 ngày đầu tiên của mỗi tháng
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


