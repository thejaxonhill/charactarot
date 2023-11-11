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
import com.fasterxml.jackson.databind.module.SimpleModule;

@Configuration
public class TarotCardConfig {

    @Value(value = "${app.host}")
    private String host;

    @Bean
    List<TarotCard> tarotCards() throws IOException {
        File cardsDataJson = new ClassPathResource("cards_data.json").getFile();
        SimpleModule sm = new SimpleModule().addAbstractTypeMapping(TarotCard.class, TarotCard.class);
        ObjectMapper om = new ObjectMapper().registerModule(sm);
        List<TarotCard> tarotCards = om.readValue(cardsDataJson, new TypeReference<>() {
        });

        return tarotCards.stream().map(this::withImageLink).toList();
    }

    private TarotCard withImageLink(TarotCard card) {
        return TarotCard.builder()
                .desc(card.getDesc())
                .imageLink(String.format("%s/api/v1/images/%d", host, card.getIntValue()))
                .intValue(card.getIntValue())
                .name(card.getName())
                .revMeaning(card.getRevMeaning())
                .shortName(card.getShortName())
                .suit(card.getSuit())
                .type(card.getType())
                .upMeaning(card.getUpMeaning())
                .value(card.getValue())
                .build();
    }

}
