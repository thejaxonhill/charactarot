package com.jhill.charactarot.tarot.components;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.jhill.charactarot.tarot.TarotCard;
import com.jhill.charactarot.tarot.port.LoadTarotCardPort;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TarotCardLoader implements LoadTarotCardPort {

    private final List<TarotCard> tarotCards;

    public Optional<TarotCard> load(Integer id) {
        return tarotCards.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst();
    }

    public List<TarotCard> loadAll() {
        return tarotCards;
    }

    public Optional<TarotCard> loadByShortName(String shortName) {
        return tarotCards.stream()
                .filter(c -> c.getShortName().equals(shortName))
                .findFirst();
    }

}
