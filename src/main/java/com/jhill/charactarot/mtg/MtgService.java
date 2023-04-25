package com.jhill.charactarot.mtg;

import com.jhill.charactarot.mtg.MtgCardService.MtgCardsRequest;

public interface MtgService<T> {

    T getAll(MtgCardsRequest cardRequest);

}
