package com.colutti.lettucePubSub.configuration;

import io.lettuce.core.RedisClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RedisClientConfiguration {

    @Value("${redis.password}")
    private String redisPassword;

    @Value("${redis.host}")
    private String redisHost;

    @Bean
    public RedisClient redisClientBean() {
        StringBuilder redisConfig = new StringBuilder();
        redisConfig.append("redis://")
                .append(redisPassword)
                .append("@")
                .append(redisHost)
                .append("/0");
        RedisClient redisClient = RedisClient.create(redisConfig.toString());
        return redisClient;
    }
}
