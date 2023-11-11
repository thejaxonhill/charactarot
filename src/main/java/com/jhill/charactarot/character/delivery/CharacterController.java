package com.jhill.charactarot.character.delivery;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jhill.charactarot.character.usecase.TarotCharacterGenerator;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/v1/character")
@RequiredArgsConstructor
public class CharacterController {

    private final TarotCharacterGenerator service;

    @GetMapping
    public ResponseEntity<Object> buildCharacter(@RequestParam(value = "card") List<String> cards) {
        return ResponseEntity.ok(service.buildCharacter(cards));
    }

}
