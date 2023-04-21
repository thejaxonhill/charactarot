package com.jhill.charactarot.mtg.model;

import java.util.List;

public interface MtgCard {

    String getArtist();

    String getCmc();

    List<String> getColors();

    List<String> getColorIdentity();

    List<ForeignName> getForeignNames();

    String getId();

    String getImageUrl();

    String getLayout();

    List<Legality> getLegalities();

    String getManaCost();

    String getMultiverseid();

    String getName();

    String getNumber();

    String getOriginalText();

    String getOriginalType();

    String getPower();

    List<String> getPrintings();

    String getRarity();

    String getSet();

    String getSetName();

    List<String> getSubtypes();

    String getText();

    String getToughness();

    String getType();

    List<String> getTypes();

    List<String> getVariations();

}
