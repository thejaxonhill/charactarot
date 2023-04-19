package com.jhill.charactarot.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.jhill.charactarot.model.TarotCard;
import com.jhill.charactarot.model.TarotCardResponse;
import com.jhill.charactarot.model.TarotCardResponseModel;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TarotCardServiceImpl implements TarotCardService {

    private final List<TarotCard> tarotCards;

    @Override
    public TarotCardResponse drawRandomCards() {
        List<TarotCard> cards = getRandomCards();
        return TarotCardResponseModel.builder()
                .numHits(cards.size())
                .cards(cards)
                .build();
    }

    @Override
    public TarotCardResponse getAllCards() {
        return TarotCardResponseModel.builder()
                .numHits(tarotCards.size())
                .cards(tarotCards)
                .build();
    }

    @Override
    public TarotCardResponse getCardByShortName(String shortName) {
        List<TarotCard> cards = tarotCards.stream()
                .filter(c -> c.getShortName().contains(shortName))
                .toList();
        return TarotCardResponseModel.builder()
                .numHits(cards.size())
                .cards(cards)
                .build();
    }

    private List<TarotCard> getRandomCards() {
        Random rand = new Random();
        List<TarotCard> randomCards = new ArrayList<>();
        while (randomCards.size() < 3) {
            TarotCard randomCard = tarotCards.get(rand.nextInt(78));
            if (!randomCards.contains(randomCard))
                randomCards.add(randomCard);
        }

        return randomCards;
    }
}
