package com.vietqr.org.grpc.reactive;

import com.example.grpc.GetUserRegisterRequest;
import com.example.grpc.UserRegister;
import com.example.grpc.UserRegisterServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Service
public class UserRegisterServiceClient {
    private static final Logger logger = LoggerFactory.getLogger(UserRegisterServiceClient.class);
    private final UserRegisterServiceGrpc.UserRegisterServiceStub asyncStub;

    public UserRegisterServiceClient(ManagedChannel channel) {
        this.asyncStub = UserRegisterServiceGrpc.newStub(channel);
    }

    public UserRegister getUserRegister(long startDate, long endDate) throws InterruptedException {
        GetUserRegisterRequest request = GetUserRegisterRequest.newBuilder()
                .setStartDate(startDate)
                .setEndDate(endDate)
                .build();

        CountDownLatch latch = new CountDownLatch(1);
        final UserRegister[] response = new UserRegister[1];

        asyncStub.getUserRegister(request, new StreamObserver<UserRegister>() {
            @Override
            public void onNext(UserRegister userRegister) {
                response[0] = userRegister;
                logger.info("Received response: {}", userRegister);
            }

            @Override
            public void onError(Throwable throwable) {
                logger.error("RPC failed: ", throwable);
                latch.countDown();
            }

            @Override
            public void onCompleted() {
                logger.info("RPC completed");
                latch.countDown();
            }
        });

        latch.await(1, TimeUnit.MINUTES);
        return response[0];
    }
}
