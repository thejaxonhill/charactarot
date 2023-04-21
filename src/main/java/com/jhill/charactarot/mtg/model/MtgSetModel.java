package com.jhill.charactarot.mtg.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MtgSetModel implements MtgSet {

    private String block;

    private List<String> booster;

    private String code;

    private List<String> expansion;

    private String gathererCode;

    private String magicCardsInfoCode;

    @JsonProperty("mkm_id")
    private Long mkmId;

    @JsonProperty("mkm_name")
    private String mkmName;

    private String name;

    private String oldCode;

    private String onlineOnly;

    private Date releaseDate;

    private String type;

}
