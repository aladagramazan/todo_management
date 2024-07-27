package com.remaladag.todo.config;

import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterConfig;
import io.github.resilience4j.ratelimiter.RateLimiterRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class RateConfig {

    @Bean
    public RateLimiterConfig rateLimiterConfig() {
        return RateLimiterConfig.custom()
                .limitRefreshPeriod(Duration.ofSeconds(10))  // 10 seconds to refresh the limitForPeriod
                .limitForPeriod(1) // 1 request per 10 seconds
                .timeoutDuration(Duration.ofSeconds(1)) // 1 second timeout
                .build();
    }
    @Bean
    public RateLimiterRegistry rateLimiterRegistry(RateLimiterConfig rateLimiterConfig) {
        return RateLimiterRegistry.of(rateLimiterConfig);
    }

    @Bean
    public Map<String, RateLimiter> rateLimiterMap(RateLimiterRegistry rateLimiterRegistry) {
        return new ConcurrentHashMap<>();
    }
}
