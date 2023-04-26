package com.jhill.charactarot.mtg;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;
import java.util.function.Consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Call;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

@Slf4j
@RequiredArgsConstructor
abstract public class AbstractMtgService<T, REQ> implements MtgService<T, REQ> {

    private static final String HOST = "api.magicthegathering.io";

    private static final String VERSION = "v1";

    private final OkHttpClient client;

    private final String basePath;

    protected abstract T deserialize(String body);

    protected abstract List<T> deserializeAll(String body);

    public T get(String id) {
        HttpUrl url = buildUrl(b -> b.addPathSegments(String.format("%s/%s/%s", VERSION, basePath, id)));
        return deserialize(send(url));
    }

    public List<T> getAll(REQ request) {
        HttpUrl url = buildUrl(b -> {
            b.addPathSegments(String.format("%s/%s", VERSION, basePath));
            setQueryParams(b, request);
        });
        return deserializeAll(send(url));
    }

    private HttpUrl buildUrl(Consumer<HttpUrl.Builder> consumer) {
        HttpUrl.Builder urlBuilder = new HttpUrl.Builder()
            .scheme("https")
            .host(HOST);
        consumer.accept(urlBuilder);
        return urlBuilder.build();
    }

    private void setQueryParams(HttpUrl.Builder urlBuilder, REQ request) {
        for (Field field : request.getClass().getDeclaredFields()) {
            try {
                field.setAccessible(true);
                if (field != null && field.get(request) != null) {
                    urlBuilder.addQueryParameter(field.getName(), field.get(request).toString());
                }
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
            return call.execute().body().string();
        } catch (IOException e) {
            log.error("{}", e.getMessage());
            throw new RuntimeException("Unable to complete call to server.");
        }
    }

}
