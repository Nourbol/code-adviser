package kz.edu.astanait.codeadviser.service.impl;

import kz.edu.astanait.codeadviser.client.OpenAiClient;
import kz.edu.astanait.codeadviser.config.OpenAiDefaultSettingsConfigProperties;
import kz.edu.astanait.codeadviser.domain.OpenAiMessage;
import kz.edu.astanait.codeadviser.domain.OpenAiSendPromptRequest;
import kz.edu.astanait.codeadviser.service.PromptSender;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatGptPromptSender implements PromptSender {

    private final OpenAiDefaultSettingsConfigProperties defaultSettings;
    private final OpenAiClient openAiClient;

    @Override
    public String sendPrompt(final String prompt) {
        var message = new OpenAiMessage(defaultSettings.role(), prompt);
        var request = new OpenAiSendPromptRequest(defaultSettings.model(), message, defaultSettings.n(), defaultSettings.temperature());
        var response = openAiClient.sendPrompt(request);
        return response.firstMessageContent();
    }
}
