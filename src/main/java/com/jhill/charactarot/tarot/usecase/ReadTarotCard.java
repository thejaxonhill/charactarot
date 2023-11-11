package com.jhill.charactarot.tarot.usecase;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jhill.charactarot.tarot.TarotCard;
import com.jhill.charactarot.tarot.exception.ReadTarotCardException;
import com.jhill.charactarot.tarot.port.LoadTarotCardPort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReadTarotCard {

    private final LoadTarotCardPort loadTarotCardPort;

    public List<TarotCard> readAllCards() {
        return loadTarotCardPort.loadAll();
    }

    public TarotCard readCard(Integer id) {
        return loadTarotCardPort.load(id)
                .orElseThrow(() -> new ReadTarotCardException("Card does not exist for given id."));
    }

    public TarotCard readCardByShortName(String shortName) {
        return loadTarotCardPort.loadByShortName(shortName)
                .orElseThrow(() -> new ReadTarotCardException("Card does not exist for given short name."));
    }

}
