package com.vietqr.org.configure;

import com.vietqr.org.service.SyncDatabaseServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class GrpcServerConfig {
    @Bean
    public Server grpcServer(SyncDatabaseServiceImpl syncDatabaseService) throws IOException {
        return ServerBuilder.forPort(50051)
                .addService(syncDatabaseService)
                .build()
                .start();
    }
}
