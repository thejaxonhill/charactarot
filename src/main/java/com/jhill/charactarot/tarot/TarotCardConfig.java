package com.jhill.charactarot.tarot;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class TarotCardConfig {

    @Value(value = "${app.host}")
    private String host;

    @Bean
    List<TarotCard> tarotCards() throws IOException {
        File cardsDataJson = new ClassPathResource("cards_data.json").getFile();
        ObjectMapper om = new ObjectMapper();
        List<TarotCard> tarotCards = om.readValue(cardsDataJson, new TypeReference<>() {
        });

        tarotCards.forEach(card -> card.setImageLink(formatImageLink(card.getName())));
        return tarotCards;
    }

    private String formatImageLink(String cardName) {
        return String.format("%s/api/v1/images/%s.jpg", host, cardName.replace(" ", "").toLowerCase());
    }

}
