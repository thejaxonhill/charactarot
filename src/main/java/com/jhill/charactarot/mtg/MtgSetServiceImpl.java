package com.jhill.charactarot.mtg;

import java.util.List;
import java.util.function.Consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jhill.charactarot.mtg.MtgSetService.MtgSetRequest;
import com.jhill.charactarot.mtg.model.MtgSet;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;

@Slf4j
public class MtgSetServiceImpl extends AbstractMtgService<MtgSet, MtgSetRequest> implements MtgSetService {

    private final ObjectMapper om;

    @Builder
    public MtgSetServiceImpl(OkHttpClient client, ObjectMapper om) {
        super(client, "sets");
        this.om = om;
    }

    @Override
    public List<MtgSet> getAll(Consumer<MtgSetRequest.MtgSetRequestBuilder> consumer) {
        MtgSetRequest.MtgSetRequestBuilder builder = MtgSetRequest.builder();
        consumer.accept(builder);
        return getAll(builder.build());
    }

    @Override
    protected MtgSet deserialize(String body) {
        return deserialize(body, MtgSetResponse.class).set();
    }

    @Override
    protected List<MtgSet> deserializeAll(String body) {
        return deserialize(body, MtgSetsResponse.class).sets();
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
