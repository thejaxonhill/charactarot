package com.jhill.charactarot.mtg;

import java.util.List;
import java.util.function.Consumer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jhill.charactarot.mtg.model.MtgCard;

import lombok.Builder;

public interface MtgCardService extends MtgService<MtgCardService.MtgCardsResponse> {

    MtgCardsResponse getAll(Consumer<MtgCardsRequest.MtgCardsRequestBuilder> consumer);

    @Builder
    record MtgCardsRequest (
            String artist,
            String cmc,
            String colors,
            String colorIdentity,
            String contains,
            String flavor,
            String gameFormat,
            String id,
            String layout,
            String language,
            String legality,
            String loyalty,
            String multiverseid,
            String name,
            String number,
            String orderBy,
            Integer page,
            Integer pageSize,
            String power,
            String rarity,
            boolean random,
            String set,
            String setName,
            String superTypes,
            String subTypes,
            String text,
            String toughness,
            String type,
            String types) {
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    record MtgCardResponse(MtgCard card) {
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    record MtgCardsResponse(List<MtgCard> cards, MtgCard card, String status) {
    }

}
