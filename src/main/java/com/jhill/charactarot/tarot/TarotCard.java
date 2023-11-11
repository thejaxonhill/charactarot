package com.jhill.charactarot.tarot;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class TarotCard {

    private final Integer id;

    private final String type;

    private final String shortName;

    private final String name;

    private final String value;

    private final int intValue;

    private final String suit;

    private final String upMeaning;

    private final String revMeaning;

    private final String desc;

    private final String imageLink;

}
