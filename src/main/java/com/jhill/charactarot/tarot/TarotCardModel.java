package com.jhill.charactarot.tarot;

import lombok.Data;

@Data
public class TarotCardModel implements TarotCard {

    private String type;

    private String shortName;

    private String name;

    private String value;

    private int intValue;

    private String suit;

    private String upMeaning;

    private String revMeaning;

    private String desc;

    private String imageLink;

}
