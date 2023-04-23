package com.jhill.charactarot.mtg;

import java.io.IOException;
import java.lang.reflect.Field;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Call;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

@Slf4j
@RequiredArgsConstructor
abstract public class AbstractMtgService<REQ, RES> implements MtgService<RES> {

    private final OkHttpClient client;

    private final ObjectMapper om;

    private final Class<RES> clazz;

    public RES send(HttpUrl url) {
        Request req = new Request.Builder().url(url).build();
        Call call = client.newCall(req);
        try {
            return deserialize(call.execute().body().string());
        } catch (IOException e) {
            log.error("{}", e.getMessage());
            throw new RuntimeException("Unable to complete call to server.");
        }
    }

    protected HttpUrl buildUrl(REQ request, String path) {
        HttpUrl.Builder urlBuilder = new HttpUrl.Builder()
                .scheme("https")
                .host("api.magicthegathering.io")
                .addPathSegments(String.format("v1/%s", path));

        for (Field field : request.getClass().getDeclaredFields()) {
            try {
                field.setAccessible(true);
                if (field != null && field.get(request) != null) {
                    urlBuilder.addQueryParameter(field.getName(), field.get(request).toString());
                }
            } catch (IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
                throw new RuntimeException("Unable to build request url.");
            }
        }

        return urlBuilder.build();
    }

    private RES deserialize(String body) {
        try {
            return om.readValue(body, clazz);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException("Unable to deserialize body.");
        }
    }
}
