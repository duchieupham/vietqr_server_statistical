package com.vietqr.org.grpc.reactive;

import com.example.grpc.GetTrMcRequest;
import com.example.grpc.TrMc;
import com.example.grpc.TrMcServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Service
public class TrMcServiceClient {
    private static final Logger logger = LoggerFactory.getLogger(TrMcServiceClient.class);
    private final TrMcServiceGrpc.TrMcServiceStub asyncStub;

    public TrMcServiceClient(ManagedChannel channel) {
        this.asyncStub = TrMcServiceGrpc.newStub(channel);
    }

    public List<TrMc> getTrMc(long startDate, long endDate) throws InterruptedException {
        GetTrMcRequest request = GetTrMcRequest.newBuilder()
                .setStartDate(startDate)
                .setEndDate(endDate)
                .build();

        CountDownLatch latch = new CountDownLatch(1);
        final List<TrMc> responses = new ArrayList<>();

        asyncStub.getTrMc(request, new StreamObserver<TrMc>() {
            @Override
            public void onNext(TrMc trMc) {
                responses.add(trMc);
                logger.info("Received response: {}", trMc);
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
        return responses;
    }
}