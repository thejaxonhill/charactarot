package com.jhill.charactarot.character.usecase;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.UnaryOperator;

import org.springframework.stereotype.Service;

import com.jhill.charactarot.character.exception.TarotCharacterGeneratorException;
import com.jhill.charactarot.tarot.TarotCard;
import com.jhill.charactarot.tarot.port.LoadTarotCardPort;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TarotCharacterGenerator {

        private static final String START_MSG = "Please build a basic Dungeons and Dragons character, with stats included, based off of the following Tarot Cards: ";

        private final LoadTarotCardPort loadTarotCardPort;
        private final OpenAiService openAiService;

        public String buildCharacter(List<String> cardShortNames) {
                return mapCardNames()
                                .andThen(appendToStartMsg)
                                .andThen(toChatMessage)
                                .andThen(toChatMessages)
                                .andThen(toChatCompletionRequest)
                                .andThen(this::callChatGpt)
                                .apply(cardShortNames);
        }

        private Function<List<String>, String> mapCardNames() {
                return cardShortNames -> cardShortNames.stream()
                                .map(loadTarotCardPort::loadByShortName)
                                .filter(Optional::isPresent)
                                .map(Optional::get)
                                .map(TarotCard::getName)
                                .reduce((s1, s2) -> String.format("%s, %s", s1, s2))
                                .orElseThrow(() -> new TarotCharacterGeneratorException("No cards were found."));
        }

        private final UnaryOperator<String> appendToStartMsg = cards -> START_MSG + cards;

        private final Function<String, ChatMessage> toChatMessage = message -> new ChatMessage("user", message);

        private final Function<ChatMessage, List<ChatMessage>> toChatMessages = message -> List.of(
                        new ChatMessage("system", "You are an expert at Dungeons and Dragons."),
                        message);

        private final Function<List<ChatMessage>, ChatCompletionRequest> toChatCompletionRequest = messages -> ChatCompletionRequest
                        .builder()
                        .messages(messages)
                        .model("gpt-3.5-turbo-0301")
                        .n(1)
                        .logitBias(new HashMap<>())
                        .build();

        private String callChatGpt(ChatCompletionRequest request) {
                StringBuilder sb = new StringBuilder();
                openAiService.streamChatCompletion(request)
                                .blockingForEach(chunk -> chunk.getChoices()
                                                .forEach(c -> sb.append(c.getMessage().getContent())));
                return sb.toString().replace("null", "");
        }

}
