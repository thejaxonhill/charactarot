package com.jhill.charactarot.tarot.delivery;

import java.io.IOException;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/images")
public class TarotImageController {

    @GetMapping(value = "/{fileName}")
    public ResponseEntity<Resource> getImage(@PathVariable(value = "fileName") String fileName) throws IOException {
        Resource imageResource = new ClassPathResource(String.format("/cards/%s", fileName));
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .contentLength(imageResource.contentLength())
                .body(new InputStreamResource(imageResource.getInputStream()));
    }

}
