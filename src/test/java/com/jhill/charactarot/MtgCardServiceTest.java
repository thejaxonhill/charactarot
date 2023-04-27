package com.jhill.charactarot;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

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
    void givenId_whenCardExists_thenNotNull() {
        Optional<MtgCard> res = service.get("3816");
        assertNotNull(res.get());
    }

    @Test
    void givenId_whenCardNotExists_thenNull() {
        Optional<MtgCard> res = service.get("3816131242");
        assertFalse(res.isPresent());
    }

}
