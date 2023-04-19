package com.jhill.charactarot.tarot;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

@Configuration
public class TarotCardConfig {

    @Bean
    TarotCardService tarotCards() throws IOException {
        File cardsDataJson = new ClassPathResource("cards_data.json").getFile();
        SimpleModule sm = new SimpleModule().addAbstractTypeMapping(TarotCard.class, TarotCardModel.class);
        ObjectMapper om = new ObjectMapper().registerModule(sm);
        List<TarotCard> tarotCards = om.readValue(cardsDataJson, new TypeReference<List<TarotCard>>() {

        });

        tarotCards.forEach(c -> c.setImageLink(
                String.format("http://localhost:8080/api/v1/images/%s.jpg",
                        c.getName().replace(" ", "").toLowerCase())));

        return new TarotCardServiceImpl(tarotCards);
    }

}
