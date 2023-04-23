package com.jhill.charactarot.mtg;

import okhttp3.HttpUrl;

public interface MtgService<T> {

    T send(HttpUrl url);

}
