package com.jhill.charactarot.mtg.model;

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
public class ForeignNameModel implements MutableForeignName {

    private String flavor;

    private String imageUrl;

    private String language;

    private Long mulitverseid;

    private String name;

    private String text;

    private String type;

}
