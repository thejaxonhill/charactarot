package com.jhill.charactarot.mtg;

import java.time.Duration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.OkHttpClient;

@Configuration
public class MtgConfig {
    
    @Bean
    MtgCardService mtgCardService() {
        return MtgCardServiceImpl.builder()
        .client(new OkHttpClient.Builder()
            .callTimeout(Duration.ofMinutes(5))
            .connectTimeout(Duration.ofMinutes(5))
            .readTimeout(Duration.ofMinutes(5))
        .build())
        .om(new ObjectMapper())
        .build();
    }
    
}
