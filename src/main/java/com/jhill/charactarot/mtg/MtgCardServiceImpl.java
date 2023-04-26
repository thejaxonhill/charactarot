package com.jhill.charactarot.mtg;

import java.util.List;
import java.util.function.Consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jhill.charactarot.mtg.MtgCardService.MtgCardRequest;
import com.jhill.charactarot.mtg.model.MtgCard;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;

@Slf4j
public class MtgCardServiceImpl extends AbstractMtgService<MtgCard, MtgCardRequest> implements MtgCardService {

     private final ObjectMapper om;

    @Builder
    public MtgCardServiceImpl(OkHttpClient client, ObjectMapper om) {
        super(client, "cards");
        this.om = om;
    }

    @Override
    public List<MtgCard> getAll(Consumer<MtgCardRequest.MtgCardRequestBuilder> consumer) {
        MtgCardRequest.MtgCardRequestBuilder builder = MtgCardRequest.builder();
        consumer.accept(builder);
        return getAll(builder.build());
    }

    @Override
    protected MtgCard deserialize(String body) {
        return deserialize(body, MtgCardResponse.class).card();
    }

    @Override
    protected List<MtgCard> deserializeAll(String body) {
        return deserialize(body, MtgCardsResponse.class).cards();
    }

    private <T> T deserialize(String body, Class<T> clazz) {
        try {
            return om.readValue(body, clazz);
        } catch (JsonProcessingException e) {
            log.error("{}", e.getMessage());
            throw new RuntimeException("Unable to deserialize response.");
        }
    }

}
