package com.jhill.charactarot.mtg.model;

public interface MutableForeignName extends ForeignName {

    void setFlavor(String flavor);

    void setImageUrl(String imageUrl);

    void setLanguage(String language);

    void setMulitverseid(Long multiverseid);

    void setName(String name);

    void setText(String text);

    void setType(String type);

}
