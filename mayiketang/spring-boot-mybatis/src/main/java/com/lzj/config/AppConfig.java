package com.lzj.config;

import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Value("${connection_timeout}")
    private int connectionTimeout;

    public int getConnectionTimeout() {
        return connectionTimeout;
    }
}
