package com.jhill.charactarot;

import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;

public class ChatGPTTest {

        @Test
        void testService() {
                OpenAiService service = new OpenAiService("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");

                ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest.builder()
                                .messages(List.of(
                                                new ChatMessage("system", "You are an expert at Dungeons and Dragons."),
                                                new ChatMessage("user",
                                                                "Please build a basic Dungeons and Dragons character based off of the Tarot Card The Hierophage")))
                                .model("gpt-3.5-turbo-0301")
                                .n(1)
                                .logitBias(new HashMap<>())
                                .build();
                StringBuilder sb = new StringBuilder();
                service.streamChatCompletion(chatCompletionRequest)
                                .blockingForEach(chunk -> chunk.getChoices()
                                                .forEach(c -> sb.append(c.getMessage().getContent())));

                System.out.println(sb.toString());

        }
}
