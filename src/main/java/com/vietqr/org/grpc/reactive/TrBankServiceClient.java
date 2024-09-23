package com.vietqr.org.grpc.reactive;

import com.example.grpc.GetTrBankRequest;
import com.example.grpc.TBank;
import com.example.grpc.TrBankServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Service
public class TrBankServiceClient {
    private static final Logger logger = LoggerFactory.getLogger(TrBankServiceClient.class);
    private final TrBankServiceGrpc.TrBankServiceStub asyncStub;

    public TrBankServiceClient(ManagedChannel channel) {
        this.asyncStub = TrBankServiceGrpc.newStub(channel);
    }

    public TBank getTrBank(long startDate, long endDate) throws InterruptedException {
        GetTrBankRequest request = GetTrBankRequest.newBuilder()
                .setStartDate(startDate)
                .setEndDate(endDate)
                .build();

        CountDownLatch latch = new CountDownLatch(1);
        final TBank[] response = new TBank[1];

        asyncStub.getTrBank(request, new StreamObserver<TBank>() {
            @Override
            public void onNext(TBank tBank) {
                response[0] = tBank;
                logger.info("Received response: {}", tBank);
            }

            @Override
            public void onError(Throwable t) {
                logger.error("RPC failed: ", t);
                latch.countDown();
            }

            @Override
            public void onCompleted() {
                logger.info("RPC completed.");
                latch.countDown();
            }
        });

        latch.await(1, TimeUnit.MINUTES);
        return response[0];
    }
}

