package com.jhill.charactarot.mtg;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jhill.charactarot.mtg.model.MtgCard;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/mtg")
public class MtgController {

    private final MtgCardService mtgCardService;

    @GetMapping(value = "/card/random")
    public ResponseEntity<MtgCard> getRandomCard(
            @RequestParam(value = "colorIdentity", required = false) String colorIdentity) {
        return ResponseEntity.ok(mtgCardService.getAll(r -> r.random(true)
                .pageSize(1)).get(0));
    }

}
