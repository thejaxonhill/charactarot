package com.jhill.charactarot.model;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TarotCardResponseModel implements TarotCardResponse {

    private final int numHits;

    private final List<TarotCard> cards;

}
