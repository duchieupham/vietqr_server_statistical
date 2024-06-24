package com.vietqr.org.client;

import com.example.grpc.GetUsersRequest;
import com.example.grpc.GetUsersResponse;
import com.example.grpc.User;
import com.example.grpc.UserServiceGrpc;
import io.grpc.ManagedChannel;
import org.springframework.stereotype.Service;


import java.util.List;



public class UserClient {
    private final UserServiceGrpc.UserServiceBlockingStub userServiceBlockingStub;

    public UserClient(ManagedChannel channel) {
        userServiceBlockingStub = UserServiceGrpc.newBlockingStub(channel);
    }

    public List<User> getRegisteredUsers(String date) {
        GetUsersRequest request = GetUsersRequest.newBuilder().setDate(date).build();
        System.out.println("Sending request to server with date: " + date);
        GetUsersResponse response;
        try {
            response = userServiceBlockingStub.getRegisteredUsers(request);
        } catch (Exception e) {
            System.err.println("Error occurred: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
        System.out.println("Received response from server");
        return response.getUsersList();
    }
}