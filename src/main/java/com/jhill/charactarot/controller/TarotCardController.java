package com.jhill.charactarot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jhill.charactarot.model.TarotCardResponse;
import com.jhill.charactarot.service.TarotCardService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/v1/cards")
@RequiredArgsConstructor
public class TarotCardController {

    private final TarotCardService tarotCardService;

    @GetMapping
    public ResponseEntity<TarotCardResponse> getAllCards() {
        return ResponseEntity.ok(tarotCardService.getAllCards());
    }

    @GetMapping(value = "/search/shortName")
    public ResponseEntity<TarotCardResponse> getCard(@RequestParam(value = "shortName") String shortName) {
        return ResponseEntity.ok(tarotCardService.getCardByShortName(shortName));
    }

    @GetMapping(value = "/random")
    public ResponseEntity<TarotCardResponse> getRandomCards() {
        return ResponseEntity.ok(tarotCardService.drawRandomCards());
    }

}
