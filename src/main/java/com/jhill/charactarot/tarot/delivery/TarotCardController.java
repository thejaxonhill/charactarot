package com.jhill.charactarot.tarot.delivery;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jhill.charactarot.tarot.TarotCard;
import com.jhill.charactarot.tarot.usecase.DrawRandomCards;
import com.jhill.charactarot.tarot.usecase.ReadTarotCard;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/v1/cards")
@RequiredArgsConstructor
public class TarotCardController {

    private final DrawRandomCards loadTarotCards;
    private final ReadTarotCard readTarotCard;

    @GetMapping
    public ResponseEntity<Page<TarotCard>> getAllCards(Pageable pageable) {
        List<TarotCard> cards = readTarotCard.readAllCards();
        return ResponseEntity.ok(new PageImpl<>(cards.stream()
                .skip(pageable.getOffset())
                .limit(pageable.getPageSize())
                .toList(), pageable, cards.size()));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TarotCard> getCard(@PathVariable(value = "id") Integer shortName) {
        TarotCard card = readTarotCard.readCard(shortName);
        return ResponseEntity.ok(card);
    }

    @GetMapping
    public ResponseEntity<TarotCard> getCardByShortName(@RequestParam(value = "shortName") String shortName) {
        TarotCard card = readTarotCard.readCardByShortName(shortName);
        return ResponseEntity.ok(card);
    }

    @GetMapping(value = "/random")
    public ResponseEntity<List<TarotCard>> getRandomCards(
            @RequestParam(value = "size", required = false) Integer size) {
        return ResponseEntity.ok(size != null
                ? loadTarotCards.drawRandomCards(size)
                : loadTarotCards.drawRandomCards());
    }

}