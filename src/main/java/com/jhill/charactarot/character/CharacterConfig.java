package com.jhill.charactarot.character;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.theokanning.openai.service.OpenAiService;

@Configuration
public class CharacterConfig {

    @Value("${openai.token}")
    String openAiToken;

    @Bean
    OpenAiService openAiService() {
        return new OpenAiService(openAiToken);
    }

}
