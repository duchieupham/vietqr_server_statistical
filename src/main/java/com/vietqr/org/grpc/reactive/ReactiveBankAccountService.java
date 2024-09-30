package com.vietqr.org.grpc.reactive;

import com.example.grpc.BankAccountStatisticsResponse;
import com.vietqr.org.client.BankAccountClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Service
public class ReactiveBankAccountService {
    @Autowired
    private BankAccountClient bankAccountClient;

    @CircuitBreaker(name = "bankAccountService", fallbackMethod = "fallbackGetBankAccountStatistics")
    @RateLimiter(name = "bankAccountService")
    public Flux<BankAccountStatisticsResponse> getBankAccountStatistics(String month) throws InterruptedException {
        return Flux.fromIterable(bankAccountClient.getBankAccountStatistics(month))
                .subscribeOn(Schedulers.boundedElastic())
                .onErrorResume(throwable -> {
                    System.err.println("Error occurred: " + throwable.getMessage());
                    return Flux.empty();
                });
    }

    public Flux<BankAccountStatisticsResponse> fallbackGetBankAccountStatistics(String month, Throwable t) {
        System.err.println("Fallback method called due to: " + t.getMessage());
        return Flux.empty();
    }


    // Phương thức mới không nhận tham số
    @CircuitBreaker(name = "bankAccountService", fallbackMethod = "fallbackGetAllBankAccountStatistics")
    @RateLimiter(name = "bankAccountService")
    public Flux<BankAccountStatisticsResponse> getAllBankAccountStatistics() throws InterruptedException {
        return Flux.fromIterable(bankAccountClient.getAllBankAccountStatistics())
                .subscribeOn(Schedulers.boundedElastic())
                .onErrorResume(throwable -> {
                    System.err.println("Error occurred: " + throwable.getMessage());
                    return Flux.empty();
                });
    }

    public Flux<BankAccountStatisticsResponse> fallbackGetAllBankAccountStatistics(Throwable t) {
        System.err.println("Fallback method called due to: " + t.getMessage());
        return Flux.empty();
    }
}
