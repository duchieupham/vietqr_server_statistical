package com.vietqr.org.grpc.reactive;

import com.example.grpc.User;
import com.vietqr.org.client.UserClient;
import com.vietqr.org.entity.UsDateEntity;
import com.vietqr.org.entity.UsMonthEntity;
import com.vietqr.org.repository.UsDateRepository;
import com.vietqr.org.repository.UsMonthRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.YearMonth;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class ReactiveUserService {
    private static final Logger logger = LoggerFactory.getLogger(ReactiveUserService.class);
    @Autowired
    private  UserClient userClient;
    @Autowired
    private UsMonthRepository usMonthRepository;

    @Autowired
    private UsDateRepository usDateRepository;

    @CircuitBreaker(name = "userService", fallbackMethod = "fallbackGetRegisteredUsers")
    @RateLimiter(name = "userService")
    public Mono<List<User>> getRegisteredUsers(String date) {
        return Mono.fromCallable(() -> {
                    userClient.getRegisteredUsers(date);
                    logger.info("Retrieced users and sumUserJson: " + userClient.getSumUserJson());
                    saveUserRegistrationDay(date, userClient.getSumUserJson());

                    long dailyCount = parseUserRegister(userClient.getSumUserJson());
                    updateUserRegistrationMonth(date, dailyCount);

                    return Collections.<User>emptyList(); // Trả về danh sách các User từ UserClient
                })
                .subscribeOn(Schedulers.boundedElastic())
                .onErrorResume(throwable -> {
                    System.err.println("Error occurred: " + throwable.getMessage());
                    return Mono.just(Collections.<User>emptyList());
                });
    }

    private void saveUserRegistrationDay(String day, String sumUserJson) {
        logger.info("Saving user registration statistics for day: " + day + " with sumUserJson: " + sumUserJson);
        //Parse user_register from sumUserJson
        long userRegister = parseUserRegister(sumUserJson);
        logger.info("Parsed user_register: " + userRegister);

        String id = UUID.randomUUID().toString();
        UsDateEntity entity = new UsDateEntity(id, day, Long.toString(userRegister));
        usDateRepository.save(entity);
    }

    private void updateUserRegistrationMonth(String day, long dailyCount) {
        YearMonth yearMonth = YearMonth.now();
        String month = yearMonth.toString();

        UsMonthEntity monthEntity = usMonthRepository.findByMonth(month);
        if (monthEntity == null) {
            monthEntity = new UsMonthEntity();
            monthEntity.setId(UUID.randomUUID().toString());
            monthEntity.setMonth(month);
            monthEntity.setUserCount(String.valueOf(dailyCount));
        } else {
            //Long.toString(Long.parseLong(monthEntity.getUserCount()) + count)
            monthEntity.setUserCount(Long.toString(Long.parseLong(monthEntity.getUserCount()) + dailyCount));
        }

        usMonthRepository.save(monthEntity);
        logger.info("Updated monthly user registration statistics for month: " + month + " with total count: " + monthEntity.getUserCount());
    }

    private long parseUserRegister(String sumUserJson) {
        try {
            // Verify if the string is a valid JSON format
            if (sumUserJson != null && sumUserJson.trim().startsWith("{")) {
                JSONObject jsonObject = new JSONObject(sumUserJson);
                return jsonObject.getLong("user_register");
            } else {
                logger.error("Invalid JSON format: " + sumUserJson);
                return 0;
            }
        } catch (JSONException e) {
            logger.error("Failed to parse user_register from sumUserJson: " + sumUserJson, e);
            return 0;
        }
    }

    public String getSumUserJson() {
        return userClient.getSumUserJson();
    }

    public Mono<List<User>> fallbackGetRegisteredUsers(String date, Throwable t) {
        System.err.println("Fallback method called due to: " + t.getMessage());
        return Mono.just(Collections.emptyList());
    }


}