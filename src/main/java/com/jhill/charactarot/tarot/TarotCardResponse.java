package com.jhill.charactarot.tarot;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TarotCardResponse {

    private final int numHits;

    private final List<TarotCard> cards;

}
