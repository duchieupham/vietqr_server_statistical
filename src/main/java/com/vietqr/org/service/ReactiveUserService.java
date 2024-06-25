package com.vietqr.org.service;

import com.example.grpc.User;
import com.vietqr.org.client.UserClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.Collections;
import java.util.List;

@Service
public class ReactiveUserService {
    private final UserClient userClient;

    @Autowired
    public ReactiveUserService(UserClient userClient) {
        this.userClient = userClient;
    }

    @CircuitBreaker(name = "userService", fallbackMethod = "fallbackGetRegisteredUsers")
    @RateLimiter(name = "userService")
    public Mono<List<User>> getRegisteredUsers(String date) {
        return Mono.fromCallable(() -> {
                    return userClient.getRegisteredUsers(date); // Trả về danh sách các User từ UserClient
                })
                .subscribeOn(Schedulers.boundedElastic())
                .onErrorResume(throwable -> {
                    System.err.println("Error occurred: " + throwable.getMessage());
                    return Mono.just(Collections.<User>emptyList());
                });
    }

    @CircuitBreaker(name = "userService", fallbackMethod = "fallbackGetRegisteredUsersInMonth")
    @RateLimiter(name = "userService")
    public Mono<List<User>> getRegisteredUsersInMonth(String month) {
        return Mono.fromCallable(() -> {
                    userClient.getRegisteredUsersInMonth(month);
                    return Collections.<User>emptyList(); // or return the processed list
                })
                .subscribeOn(Schedulers.boundedElastic())
                .onErrorResume(throwable -> {
                    System.err.println("Error occurred: " + throwable.getMessage());
                    return Mono.just(Collections.<User>emptyList());
                });
    }

    public String getSumUserJson() {
        return userClient.getSumUserJson();
    }

    public Mono<List<User>> fallbackGetRegisteredUsers(String date, Throwable t) {
        System.err.println("Fallback method called due to: " + t.getMessage());
        return Mono.just(Collections.emptyList());
    }

    public Mono<List<User>> fallbackGetRegisteredUsersInMonth(String month, Throwable t) {
        System.err.println("Fallback method called due to: " + t.getMessage());
        return Mono.just(Collections.emptyList());
    }
}