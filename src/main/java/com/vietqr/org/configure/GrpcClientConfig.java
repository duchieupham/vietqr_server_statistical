package com.vietqr.org.configure;

import com.vietqr.org.client.UserClient;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GrpcClientConfig {
    @Bean
    public ManagedChannel managedChannel() {
        return ManagedChannelBuilder.forAddress("localhost", 9090)
                .usePlaintext() // Sử dụng PLAINTEXT
                .build();
    }

    @Bean
    public UserClient userClient(ManagedChannel managedChannel) {
        return new UserClient(managedChannel);
    }
}
