package com.jhill.charactarot.tarot;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TarotCardServiceImpl implements TarotCardService {

    private final List<TarotCard> tarotCards;

    @Override
    public List<TarotCard> drawRandomCards() {
        return getRandomCards();
    }

    @Override
    public List<TarotCard> getAllCards() {
        return tarotCards;
    }

    @Override
    public TarotCard getCardByShortName(String shortName) {
        return tarotCards.stream()
                .filter(c -> c.getShortName().equals(shortName))
                .toList().get(0);
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
