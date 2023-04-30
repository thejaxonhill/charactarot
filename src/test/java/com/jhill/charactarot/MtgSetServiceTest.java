package com.jhill.charactarot;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.time.Duration;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jhill.charactarot.mtg.MtgSetService;
import com.jhill.charactarot.mtg.MtgSetServiceImpl;
import com.jhill.charactarot.mtg.model.MtgCard;

import okhttp3.OkHttpClient;

public class MtgSetServiceTest {

    MtgSetService service = MtgSetServiceImpl.builder()
            .om(new ObjectMapper())
            .client(new OkHttpClient.Builder()
                    .callTimeout(Duration.ofMinutes(5))
                    .connectTimeout(Duration.ofMinutes(5))
                    .readTimeout(Duration.ofMinutes(5))
                    .build())
            .build();
    
    @Test
    void givenSetId_WhenGenBooster_ResOk() {
        List<MtgCard> booster = service.generateBooster("ktk");
        assertFalse(booster.isEmpty());
    }
}
