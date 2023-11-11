package com.jhill.charactarot.tarot.usecase;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.jhill.charactarot.tarot.TarotCard;
import com.jhill.charactarot.tarot.exception.ReadTarotCardException;
import com.jhill.charactarot.tarot.port.LoadTarotCardPort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DrawRandomCards {

    private final LoadTarotCardPort loadTarotCardPort;

    public List<TarotCard> drawRandomCards() {
        return drawRandomCards(3);
    }

    public List<TarotCard> drawRandomCards(int size) {
        List<TarotCard> randomCards = new ArrayList<>();
        while (randomCards.size() < size) {
            randomCards.add(getRandomTarotCard(randomCards));
        }
        return randomCards;
    }

    private TarotCard getRandomTarotCard(List<TarotCard> tarotCards) {
        Integer rand = new Random().nextInt(78);
        return tarotCards.stream()
                .map(TarotCard::getId)
                .anyMatch(id -> rand.equals(id))
                        ? getRandomTarotCard(tarotCards)
                        : loadTarotCardPort.load(rand)
                                .orElseThrow(() -> new ReadTarotCardException("Failed to find random tarot cards."));
    }

}
