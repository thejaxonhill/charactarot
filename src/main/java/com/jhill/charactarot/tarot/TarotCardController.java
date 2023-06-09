package com.jhill.charactarot.tarot;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/v1/cards")
@RequiredArgsConstructor
public class TarotCardController {

    private final TarotCardService tarotCardService;

    @GetMapping
    public ResponseEntity<TarotCardsResponse> getAllCards() {
        return ResponseEntity.ok(mapResponse(tarotCardService.getAllCards()));
    }

    @GetMapping(value = "/search/shortName")
    public ResponseEntity<TarotCard> getCard(@RequestParam(value = "shortName") String shortName) {

        return ResponseEntity.ok(tarotCardService.getCardByShortName(shortName));

    }

    @CrossOrigin
    @GetMapping(value = "/random")
    public ResponseEntity<TarotCardsResponse> getRandomCards(
            @RequestParam(value = "size", required = false) Integer size) {

        return ResponseEntity.ok(mapResponse(size != null
                ? tarotCardService.drawRandomCards(size)
                : tarotCardService.drawRandomCards()));

    }

    private TarotCardsResponse mapResponse(List<TarotCard> cards) {

        return new TarotCardsResponse(cards.size(), cards);

    }

    private record TarotCardsResponse(int numHits, List<TarotCard> cards) {
    }

}
