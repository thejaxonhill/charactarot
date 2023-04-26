package com.jhill.charactarot.mtg;

import java.util.List;

public interface MtgService<T, REQ> {

    T get(String id);

    List<T> getAll(REQ request);

}
