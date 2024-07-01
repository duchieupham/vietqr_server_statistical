package com.vietqr.org.client;

import com.example.grpc.*;
import io.grpc.ManagedChannel;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;


public class UserClient {

    private static final Logger logger = LoggerFactory.getLogger(UserClient.class);
    private final UserServiceGrpc.UserServiceStub userServiceStub;
    private String sumUserJson;

    public UserClient(ManagedChannel channel) {
        userServiceStub = UserServiceGrpc.newStub(channel);
    }

    public List<User> getRegisteredUsers(String date) throws InterruptedException {
        GetUsersRequest request = GetUsersRequest.newBuilder().setDate(date).build();
        CountDownLatch latch = new CountDownLatch(1);
        List<User> users = new ArrayList<>();

        userServiceStub.getRegisteredUsers(request, new StreamObserver<User>() {
            @Override
            public void onNext(User user) {
                if ("summary".equals(user.getId())) {
                    sumUserJson = user.getSumUserJson();
                } else {
                    users.add(user);
                }
                logger.info("Received user: " + user);
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
        return users;
    }

    public List<User> getRegisteredUsersInMonth(String month) throws InterruptedException {
        GetUsersRequest request = GetUsersRequest.newBuilder().setDate(month).build();
        CountDownLatch latch = new CountDownLatch(1);
        List<User> users = new ArrayList<>();

        userServiceStub.getRegisteredUsersInMonth(request, new StreamObserver<User>() {
            @Override
            public void onNext(User user) {
                if ("summary".equals(user.getId())) {
                    sumUserJson = user.getSumUserJson();
                } else {
                    users.add(user);
                }
                logger.info("Received user: " + user);
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
        return users;
    }



    public String getSumUserJson() {
        return sumUserJson;
    }
}