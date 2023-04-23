package com.jhill.charactarot.mtg;

import java.util.List;
import java.util.function.Consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jhill.charactarot.mtg.model.MtgCard;
import com.jhill.charactarot.mtg.MtgCardService.MtgCardsRequest;
import com.jhill.charactarot.mtg.MtgCardService.MtgCardsResponse;

import lombok.Builder;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

public class MtgCardServiceImpl extends AbstractMtgService<MtgCardsRequest, MtgCardsResponse>
        implements MtgCardService {

    @Builder
    public MtgCardServiceImpl(OkHttpClient client, ObjectMapper om) {
        super(client, om, MtgCardsResponse.class);
    }

    public List<MtgCard> getAllCards() {
        return getAllCards(0, 100);
    }

    public List<MtgCard> getAllCards(int page, int pageSize) {
        return getAllCards(r -> r.page(page).pageSize(pageSize));
    }

    public List<MtgCard> getAllCards(Consumer<MtgCardsRequest.MtgCardsRequestBuilder> consumer) {
        MtgCardsRequest.MtgCardsRequestBuilder builder = MtgCardsRequest.builder();
        consumer.accept(builder);
        return getAllCards(builder.build());
    }

    public List<MtgCard> getAllCards(MtgCardsRequest cardRequest) {
        HttpUrl url = buildUrl(cardRequest, "cards");
        return send(url).cards();
    }

}
