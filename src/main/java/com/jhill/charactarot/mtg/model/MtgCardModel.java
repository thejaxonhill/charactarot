package com.jhill.charactarot.mtg.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MtgCardModel implements MutableMtgCard {

    private String artist;

    private String cmc;

    private List<String> colors;

    private List<String> colorIdentity;

    private List<ForeignName> foreignNames;

    private String id;

    private String imageUrl;

    private String layout;

    private List<Legality> legalities;

    private String manaCost;

    private String multiverseid;

    private String name;

    private String number;

    private String originalText;

    private String originalType;

    private String power;

    private List<String> printings;

    private String rarity;

    private String set;

    private String setName;

    private List<String> subtypes;

    private String text;

    private String toughness;

    private String type;

    private List<String> types;

    private List<String> variations;

}
