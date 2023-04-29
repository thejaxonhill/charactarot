package com.jhill.charactarot.mtg;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Call;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Slf4j
@RequiredArgsConstructor
abstract public class AbstractMtgService<T, REQ> implements MtgService<T, REQ> {

    private static final String HOST = "https://api.magicthegathering.io/v1/";

    private final String basePath;

    private final OkHttpClient client;

    private final ObjectMapper om;

    protected abstract T deserialize(String body);

    protected abstract List<T> deserializeAll(String body);

    protected <R> R deserialize(String body, Class<R> clazz) {
        try {
            return om.readValue(body, clazz);
        } catch (JsonProcessingException e) {
            log.error("Unable to deserialize response body: {}", body);
            throw new RuntimeException("Unable to deserialize response.");
        }
    }

    public Optional<T> get(String id) {
        HttpUrl url = buildUrl(b -> b.addPathSegment(id));
        T obj = deserialize(send(url));
        return Optional.ofNullable(obj);
    }

    public List<T> getAll(REQ request) {
        HttpUrl url = buildUrl(b -> setQueryParams(b, request));
        List<T> objs = deserializeAll(send(url));
        return objs != null ? objs : new ArrayList<>();
    }

    private HttpUrl buildUrl(Consumer<HttpUrl.Builder> consumer) {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(HOST + basePath).newBuilder();
        consumer.accept(urlBuilder);
        return urlBuilder.build();
    }

    private void setQueryParams(HttpUrl.Builder urlBuilder, REQ request) {
        for (Field field : request.getClass().getDeclaredFields()) {
            try {
                field.setAccessible(true);
                Object obj = field.get(request);
                if (obj != null)
                    urlBuilder.addQueryParameter(field.getName(), obj.toString());
            } catch (IllegalArgumentException | IllegalAccessException e) {
                log.error("{}", e.getMessage());
                throw new RuntimeException("Unable to build request url.");
            }
        }
    }

    private String send(HttpUrl url) {
        Request req = new Request.Builder().url(url).build();
        Call call = client.newCall(req);
        try {
            Response response = call.execute();
            log.info("{} : Status {}", url.toString(), response.code());
            return response.body().string();
        } catch (IOException e) {
            log.error("{}", e.getMessage());
            throw new RuntimeException("Unable to complete call to server.");
        }
    }

}
