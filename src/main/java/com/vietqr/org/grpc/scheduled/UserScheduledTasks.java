package com.vietqr.org.grpc.scheduled;

import com.vietqr.org.grpc.reactive.ReactiveUserService;
import com.vietqr.org.service.TrDateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class UserScheduledTasks {
    private static final Logger logger = LoggerFactory.getLogger(UserScheduledTasks.class);
    @Autowired
    private ReactiveUserService reactiveUserService;
    @Autowired
    private TrDateService trDateService;

    @Scheduled(cron = "0 * * * * ?")
    //@Scheduled(cron = "0 0 0 * * ?")
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
}


