package com.jhill.charactarot.mtg;

import java.util.function.Consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jhill.charactarot.mtg.MtgCardService.MtgCardsRequest;
import com.jhill.charactarot.mtg.MtgCardService.MtgCardsResponse;

import lombok.Builder;
import okhttp3.OkHttpClient;

public class MtgCardServiceImpl extends AbstractMtgService<MtgCardsRequest, MtgCardsResponse>
        implements MtgCardService {

    @Builder
    public MtgCardServiceImpl(OkHttpClient client, ObjectMapper om) {
        super(client, om, MtgCardsResponse.class, "cards");
    }

    public MtgCardsResponse getAll(Consumer<MtgCardsRequest.MtgCardsRequestBuilder> consumer) {
        MtgCardsRequest.MtgCardsRequestBuilder builder = MtgCardsRequest.builder();
        consumer.accept(builder);
        return getAll(builder.build());
    }

}
