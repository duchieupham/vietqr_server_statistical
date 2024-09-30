package com.vietqr.org.grpc.reactive;

import com.example.grpc.TrSysServiceGrpc;
import com.example.grpc.GetTrSysRequest;
import com.example.grpc.TSys;
import io.grpc.ManagedChannel;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Service
public class TrSysServiceClient {
    private static final Logger logger = LoggerFactory.getLogger(TrSysServiceClient.class);
    private final TrSysServiceGrpc.TrSysServiceStub asyncStub;

    public TrSysServiceClient(ManagedChannel channel) {
        this.asyncStub = TrSysServiceGrpc.newStub(channel);
    }

    public TSys getTrSys(long startDate, long endDate) throws InterruptedException {
        GetTrSysRequest request = GetTrSysRequest.newBuilder()
                .setStartDate(startDate)
                .setEndDate(endDate)
                .build();

        CountDownLatch latch = new CountDownLatch(1);
        final TSys[] response = new TSys[1];

        asyncStub.getTrSys(request, new StreamObserver<TSys>() {
            @Override
            public void onNext(TSys tsys) {
                response[0] = tsys;
                logger.info("Received response: {}", tsys);
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
