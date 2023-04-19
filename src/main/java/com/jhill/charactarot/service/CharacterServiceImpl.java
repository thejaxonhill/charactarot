package com.jhill.charactarot.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {

        private final TarotCardService tarotCardService;

        @Override
        public String buildCharacter(List<String> cardShortNames) {
                OpenAiService service = new OpenAiService("");

                StringBuilder message = new StringBuilder(
                                "Please build a basic Dungeons and Dragons character, with stats included, based off of the following Tarot Cards: ");

                cardShortNames.forEach(
                                s -> message.append(tarotCardService.getCardByShortName(s).getCards().get(0).getName())
                                                .append(", "));

                ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest.builder()
                                .messages(List.of(
                                                new ChatMessage("system", "You are an expert at Dungeons and Dragons."),
                                                new ChatMessage("user", message.toString())))
                                .model("gpt-3.5-turbo-0301")
                                .n(1)
                                .logitBias(new HashMap<>())
                                .build();
                StringBuilder sb = new StringBuilder();
                service.streamChatCompletion(chatCompletionRequest)
                                .blockingForEach(chunk -> chunk.getChoices()
                                                .forEach(c -> sb.append(c.getMessage().getContent())));
                return sb.toString().replace("null", "");
        }

}
