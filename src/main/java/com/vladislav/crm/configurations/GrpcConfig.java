package com.vladislav.crm.configurations;

import io.grpc.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class GrpcConfig {

    @Bean
    Server server(
            @Value("${app.grpc.server.port}") Integer port,
            @Autowired(required = false) List<BindableService> services,
            @Autowired(required = false) List<ServerInterceptor> interceptors
    ) {
        final ServerBuilder<?> serverBuilder = ServerBuilder.forPort(port);
        if (interceptors != null) {
            interceptors.forEach(serverBuilder::intercept);
        }
        if (services != null) {
            services.forEach(serverBuilder::addService);
        }
        return serverBuilder.build();
    }

    @Bean
    Metadata.Key<String> authorizationKey() {
        return Metadata.Key.of("Authorization", Metadata.ASCII_STRING_MARSHALLER);
    }
}

