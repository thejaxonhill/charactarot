package com.jhill.charactarot.mtg;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jhill.charactarot.mtg.model.MtgCard;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import okhttp3.Call;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

@Builder
@RequiredArgsConstructor
public class MtgCardServiceImpl implements MtgCardService {

    private final ObjectMapper om;

    private final OkHttpClient client;

    public List<MtgCard> getAllCards() {
        return getAllCards(0, 100);
    }

    public List<MtgCard> getAllCards(int page, int pageSize) {
        return deserialize(send("cards"), MtgCardsResponse.class).cards();
    }

    private record MtgCardsResponse(List<MtgCard> cards) {
    }

    private <T> T deserialize(String body, Class<T> klass) {
        try {
            return om.readValue(body, klass);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException("Unable to deserialize body.");
        }
    }

    private String send(String path) {
        HttpUrl url = new HttpUrl.Builder()
                .scheme("https")
                .host("api.magicthegathering.io")
                .addPathSegments(String.format("v1/%s", path))
                .build();
        Request req = new Request.Builder().url(url).build();
        Call call = client.newCall(req);
        try {
            return call.execute().body().string();
        } catch (IOException e) {
            throw new RuntimeException("Unable to complete call to server.");
        }
    }

}
