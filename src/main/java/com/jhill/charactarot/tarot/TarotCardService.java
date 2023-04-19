package com.jhill.charactarot.tarot;

import java.util.List;

public interface TarotCardService {

    List<TarotCard> getAllCards();

    TarotCard getCardByShortName(String shortName);

    List<TarotCard> drawRandomCards();

}
