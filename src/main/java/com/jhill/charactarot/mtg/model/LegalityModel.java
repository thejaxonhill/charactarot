package com.jhill.charactarot.mtg.model;

import lombok.Data;

@Data
public class LegalityModel implements MutableLegality {

    private String format;

    private String legality;

}
