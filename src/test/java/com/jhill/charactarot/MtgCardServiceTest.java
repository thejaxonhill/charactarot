package com.jhill.charactarot;

import static org.junit.jupiter.api.Assertions.assertFalse;

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
            .client(new OkHttpClient.Builder().build())
            .build();

    @Test
    void testGetllCards() {
        List<MtgCard> cards = service.getAllCards();

        assertFalse(cards.isEmpty());
    }

}
