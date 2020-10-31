package com.vladislav.crm.configurations;

import com.proto.report.ReportServiceGrpc;
import com.proto.report.ReportServiceGrpc.ReportServiceStub;
import io.grpc.Channel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReportGrpcConfig {

    @Value("${app.grpc.report.host}")
    private String host;

    @Value("${app.grpc.report.port}")
    private Integer port;

    @Bean
    Channel reportChannel() {
        return ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext()
                .build();
    }

    @Bean
    ReportServiceStub reportService(Channel reportChannel) {
        return ReportServiceGrpc.newStub(reportChannel);
    }
}
