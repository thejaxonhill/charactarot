package com.jhill.charactarot.mtg.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LegalityModel implements MutableLegality {

    private String format;

    private String legality;

}
