package com.jhill.charactarot.mtg.model;

import java.util.List;

public interface MutableMtgCard extends MtgCard {

    void setArtist(String artist);

    void setCmc(String cmc);

    void setColors(List<String> colors);

    void setColorIdentity(List<String> colorIdentities);

    void setForeignNames(List<ForeignName> foreignNames);

    void setId(String id);

    void setImageUrl(String imageUrl);

    void setLayout(String layout);

    void setLegalities(List<Legality> legalities);

    void setManaCost(String manaCost);

    void setMultiverseid(String multiverseid);

    void setName(String name);

    void setNumber(String number);

    void setOriginalText(String originalText);

    void setOriginalType(String originalType);

    void setPower(String power);

    void setPrintings(List<String> printing);

    void setRarity(String rarity);

    void setSet(String set);

    void setSetName(String setName);

    void setSubtypes(List<String> subtypes);

    void setText(String text);

    void setToughness(String toughness);

    void setType(String type);

    void setTypes(List<String> types);

    void setVariations(List<String> variations);

}
