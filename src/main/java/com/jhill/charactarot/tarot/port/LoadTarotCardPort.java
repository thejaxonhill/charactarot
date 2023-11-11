package com.jhill.charactarot.tarot.port;

import java.util.List;
import java.util.Optional;

import com.jhill.charactarot.tarot.TarotCard;

public interface LoadTarotCardPort {

    Optional<TarotCard> load(Integer id);

    List<TarotCard> loadAll();

    Optional<TarotCard> loadByShortName(String shortName);

}
