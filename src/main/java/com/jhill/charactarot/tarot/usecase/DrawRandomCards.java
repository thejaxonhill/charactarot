package com.jhill.charactarot.tarot.usecase;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.jhill.charactarot.tarot.TarotCard;
import com.jhill.charactarot.tarot.port.LoadTarotCardPort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DrawRandomCards {

    private final LoadTarotCardPort loadTarotCardPort;

    public List<TarotCard> drawRandomCards(Integer size, List<Integer> omit) {
        List<TarotCard> randomCards = loadTarotCardPort.loadAll().stream()
                .filter(card -> omit == null || omit.contains(card.getId()))
                .collect(Collectors.toList());
        Collections.shuffle(randomCards);
        return randomCards.stream().limit(size).toList();
    }

}
