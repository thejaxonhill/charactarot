package com.jhill.charactarot.mtg;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jhill.charactarot.mtg.model.MtgCard;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MtgCardServiceImpl {

    private final ObjectMapper om;

    public List<MtgCard> getAllCards() {
        return null;
    }

}
