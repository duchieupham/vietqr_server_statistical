package com.vietqr.org.grpc.reactive;

import com.example.grpc.GetSbDurationRequest;
import com.example.grpc.SbDuration;
import com.example.grpc.SbDurationServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Service
public class SbDurationServiceClient {
    private static final Logger logger = LoggerFactory.getLogger(SbDurationServiceClient.class);
    private final SbDurationServiceGrpc.SbDurationServiceStub asyncStub;

    public SbDurationServiceClient(ManagedChannel channel) {
        this.asyncStub = SbDurationServiceGrpc.newStub(channel);
    }

    public SbDuration getSbDuration(long expired, long nearingExpiration) throws InterruptedException {
        GetSbDurationRequest request = GetSbDurationRequest.newBuilder()
                .setExpired(expired)
                .setNearingExpiration(nearingExpiration)
                .build();

        CountDownLatch latch = new CountDownLatch(1);
        final SbDuration[] response = new SbDuration[1];

        asyncStub.getSbDuration(request, new StreamObserver<SbDuration>() {
            @Override
            public void onNext(SbDuration sbDuration) {
                response[0] = sbDuration;
                logger.info("Received response: {}", sbDuration);
            }

            @Override
            public void onError(Throwable throwable) {
                logger.error("RPC failed: ", throwable);
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
