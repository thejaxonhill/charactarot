package com.jhill.charactarot.mtg;

import java.util.List;
import java.util.Optional;

public interface MtgService<T, REQ> {

    Optional<T> get(String id);

    List<T> getAll(REQ request);

}
