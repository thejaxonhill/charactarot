package com.jhill.charactarot.mtg.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = MtgSetModel.class)
public interface MtgSet {

    String getBlock();

    List<String> getBooster();

    String getCode();

    List<String> getExpansion();

    String getGathererCode();

    String getMagicCardsInfoCode();

    Long getMkmId();

    String getMkmName();

    String getName();

    String getOldCode();

    String getOnlineOnly();

    Date getReleaseDate();

    String getType();
}
