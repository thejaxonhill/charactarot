package com.jhill.charactarot.model;

public interface TarotCard {

    String getType();

    String getShortName();

    String getName();

    String getValue();

    int getIntValue();

    String getSuit();

    String getUpMeaning();

    String getRevMeaning();

    String getDesc();

    String getImageLink();

    void setType(String type);

    void setShortName(String shortName);

    void setName(String name);

    void setValue(String value);

    void setIntValue(int value);

    void setSuit(String suit);

    void setUpMeaning(String upMeaning);

    void setRevMeaning(String revMeaning);

    void setDesc(String Desc);

    void setImageLink(String imageLink);

}
