package com.vietqr.org.client;

import com.vietqr.org.SyncDatabaseServiceGrpc;
import com.vietqr.org.SynchDataProto;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
@Service
public class SyncDatabaseClient {
    private final SyncDatabaseServiceGrpc.SyncDatabaseServiceBlockingStub syncDatabaseServiceBlockingStub ;

    public SyncDatabaseClient(@Value("${grpc.server.host}")String host , @Value("${grpc.server.port}") int port){
        ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext()
                .build();
        syncDatabaseServiceBlockingStub = SyncDatabaseServiceGrpc.newBlockingStub(channel);
    }

    public void syncDatabase(String date){
        SynchDataProto.SyncDatabaseRequest request = SynchDataProto.SyncDatabaseRequest.newBuilder().setDate(date).build();
        SynchDataProto.SyncDatabaseResponse respone = syncDatabaseServiceBlockingStub.syncDatabase(request);
        System.out.println("Respone status: " + respone.getStatus());
        System.out.println("Respone Message: " + respone.getMessage());
    }
    @Scheduled(cron = "*/30 * * * * *") // Chạy mỗi 30 giây
    // @Scheduled(cron = "0 0 0 * * ?") // Chạy hàng ngày vào lúc nửa đêm
    public void syncDatabaseDaily() {
        LocalDate today = LocalDate.now();
        String date = today.format(DateTimeFormatter.ISO_LOCAL_DATE);
        System.out.println("Syncing for date: " + date);
        syncDatabase(date);
    }

}
