package com.vietqr.org.service;

import com.example.grpc.User;
import com.vietqr.org.client.UserClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ScheduledTasks {
    private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);
    private final UserClient userClient;
    private final TrDateService trDateService;

    @Autowired
    public ScheduledTasks(UserClient userClient, TrDateService trDateService) {
        this.userClient = userClient;
        this.trDateService = trDateService;
    }


//        @Scheduled(cron = "0 * * * * ?")
//    //@Scheduled(cron = "0 0 0 * * ?") // Lịch chạy lúc 00:00 mỗi ngày
//    public void syncUserStatistics() {
//        String yesterday = LocalDate.now().minusDays(1).toString();
//        List<User> users;
//        try {
//            users = userClient.getRegisteredUsers(yesterday);
//            System.out.println("Received " + users.size() + " users");
//        } catch (Exception e) {
//            System.err.println("Failed to fetch registered users: " + e.getMessage());
//            return;
//        }
//        long count = users.size();
//        trDateService.saveUserStatistics(count, yesterday);
//    }
    @Scheduled(cron = "0 * * * * ?")
    // @Scheduled(cron = "0 0 0 * * ?") // Lịch chạy lúc 00:00 mỗi ngày
    public void syncUserStatistics() {
        String yesterday = LocalDate.now().minusDays(1).toString();
        List<User> users;
        String sumUserJson;
        try {
            users = userClient.getRegisteredUsers(yesterday);
            sumUserJson = userClient.getSumUserJson();  // Lấy sumUserJson
            logger.info("Received " + users.size() + " users");
            logger.info("Sum User JSON: " + sumUserJson);
        } catch (Exception e) {
            logger.error("Failed to fetch registered users: " + e.getMessage());
            return;
        }
        long count = users.size();
        trDateService.saveUserStatistics(count, sumUserJson, yesterday);
    }
}
