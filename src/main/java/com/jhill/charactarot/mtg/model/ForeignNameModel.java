package com.jhill.charactarot.mtg.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ForeignNameModel implements MutableForeignName {

    private String flavor;

    private String imageUrl;

    private String language;

    private Long mulitverseid;

    private String name;

    private String text;

    private String type;

}
