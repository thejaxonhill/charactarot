package com.jhill.charactarot.mtg;

import java.util.List;
import java.util.function.Consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jhill.charactarot.mtg.MtgSetService.MtgSetRequest;
import com.jhill.charactarot.mtg.model.MtgSet;

import lombok.Builder;
import okhttp3.OkHttpClient;

public class MtgSetServiceImpl extends AbstractMtgService<MtgSet, MtgSetRequest> implements MtgSetService {

    @Builder
    public MtgSetServiceImpl(OkHttpClient client, ObjectMapper om) {
        super("sets", client, om);
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

}
