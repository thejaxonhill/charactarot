package com.jhill.charactarot.tarot.usecase;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jhill.charactarot.tarot.TarotCard;
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
        List<TarotCard> randomCards = loadTarotCardPort.loadAll();
        Collections.shuffle(randomCards);
        return randomCards.stream().limit(size).toList();
    }

}
