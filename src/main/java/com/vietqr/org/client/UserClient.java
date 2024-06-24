package com.vietqr.org.client;

import com.example.grpc.GetUsersRequest;
import com.example.grpc.GetUsersResponse;
import com.example.grpc.User;
import com.example.grpc.UserServiceGrpc;
import io.grpc.ManagedChannel;

import java.util.List;


public class UserClient {
    private final UserServiceGrpc.UserServiceBlockingStub userServiceBlockingStub;
    private GetUsersResponse lastResponse;
    private GetUsersResponse response;
    public UserClient(ManagedChannel channel) {
        userServiceBlockingStub = UserServiceGrpc.newBlockingStub(channel);
    }

//    public List<User> getRegisteredUsers(String date) {
//        GetUsersRequest request = GetUsersRequest.newBuilder().setDate(date).build();
//        System.out.println("Sending request to server with date: " + date);
//
//        try {
//            response = userServiceBlockingStub.getRegisteredUsers(request);
//        } catch (Exception e) {
//            System.err.println("Error occurred: " + e.getMessage());
//            e.printStackTrace();
//            throw e;
//        }
//        System.out.println("Received response from server");
//        return response.getUsersList();
//    }
public List<User> getRegisteredUsers(String date) {
    GetUsersRequest request = GetUsersRequest.newBuilder().setDate(date).build();
    System.out.println("Sending request to server with date: " + date);
    try {
        lastResponse = userServiceBlockingStub.getRegisteredUsers(request);
        System.out.println("Received response from server");

        // Xử lý trường sumUser
        String sumUserJson = lastResponse.getSumUser();
        System.out.println("Sum User JSON: " + sumUserJson);
    } catch (Exception e) {
        System.err.println("Error occurred: " + e.getMessage());
        e.printStackTrace();
        throw e;
    }
    return lastResponse.getUsersList();
}

    public String getSumUserJson() {
        if (lastResponse != null) {
            return lastResponse.getSumUser();
        }
        return "";
    }
}