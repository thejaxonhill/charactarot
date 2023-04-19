package com.jhill.charactarot.model;

import java.util.List;

public interface TarotCardResponse {

    int getNumHits();

    List<TarotCard> getCards();

}
