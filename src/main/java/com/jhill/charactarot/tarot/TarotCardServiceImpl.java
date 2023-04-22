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
        return drawRandomCards(3);
    }

    @Override
    public List<TarotCard> drawRandomCards(int size) {
        return getRandomCards(size);
    }

    private List<TarotCard> getRandomCards(int size) {
        Random rand = new Random();
        List<TarotCard> randomCards = new ArrayList<>();
        while (randomCards.size() < size) {
            TarotCard randomCard = tarotCards.get(rand.nextInt(78));
            if (!randomCards.contains(randomCard))
                randomCards.add(randomCard);
        }

        return randomCards;
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

}
