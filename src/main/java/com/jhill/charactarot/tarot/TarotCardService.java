package com.jhill.charactarot.tarot;

public interface TarotCardService {

    TarotCardResponse getAllCards();

    TarotCardResponse getCardByShortName(String shortName);

    TarotCardResponse drawRandomCards();

}
