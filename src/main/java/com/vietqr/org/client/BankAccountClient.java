package com.vietqr.org.client;

import com.example.grpc.BankAccountServiceGrpc;
import com.example.grpc.BankAccountStatisticsResponse;
import com.example.grpc.GetBankAccountStatisticsRequest;
import io.grpc.ManagedChannel;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
@Component
public class BankAccountClient
{
    private static final Logger logger = LoggerFactory.getLogger(BankAccountClient.class);
    private final BankAccountServiceGrpc.BankAccountServiceStub bankAccountServiceStub;

    public BankAccountClient(ManagedChannel channel) {
        bankAccountServiceStub = BankAccountServiceGrpc.newStub(channel);
    }

    public List<BankAccountStatisticsResponse> getBankAccountStatistics(String month) throws InterruptedException {
        GetBankAccountStatisticsRequest request = GetBankAccountStatisticsRequest.newBuilder().setMonth(month).build();
        CountDownLatch latch = new CountDownLatch(1);
        List<BankAccountStatisticsResponse> responses = new ArrayList<>();

        bankAccountServiceStub.getBankAccountStatistics(request, new StreamObserver<BankAccountStatisticsResponse>() {
            @Override
            public void onNext(BankAccountStatisticsResponse response) {
                responses.add(response);
                logger.info("Received bank account statistics: " + response);
            }

            @Override
            public void onError(Throwable t) {
                logger.error("Error: ", t);
                latch.countDown();
            }

            @Override
            public void onCompleted() {
                logger.info("Stream completed");
                latch.countDown();
            }
        });

        latch.await(1, TimeUnit.MINUTES);
        return responses;
    }
}
