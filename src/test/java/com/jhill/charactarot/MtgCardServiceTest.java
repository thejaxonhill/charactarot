package com.jhill.charactarot;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.Duration;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jhill.charactarot.mtg.MtgCardService;
import com.jhill.charactarot.mtg.MtgCardServiceImpl;
import com.jhill.charactarot.mtg.model.MtgCard;

import okhttp3.OkHttpClient;

public class MtgCardServiceTest {

    MtgCardService service = MtgCardServiceImpl.builder()
            .om(new ObjectMapper())
            .client(new OkHttpClient.Builder()
                    .callTimeout(Duration.ofMinutes(5))
                    .connectTimeout(Duration.ofMinutes(5))
                    .readTimeout(Duration.ofMinutes(5))
                    .build())
            .build();

    @Test
    void testGetAll_withConsumer() {
        List<MtgCard> res = service.getAll(r -> r.pageSize(10));
        assertFalse(res.isEmpty());
    }

    @Test
    void testGet() {
        MtgCard res = service.get("386616");
        assertNotNull(res);
    }

}
