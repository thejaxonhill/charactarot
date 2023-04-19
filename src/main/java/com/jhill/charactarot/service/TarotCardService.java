package com.jhill.charactarot.service;

import com.jhill.charactarot.model.TarotCardResponse;

public interface TarotCardService {

    TarotCardResponse getAllCards();

    TarotCardResponse getCardByShortName(String shortName);

    TarotCardResponse drawRandomCards();

}
