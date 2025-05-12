package kz.edu.astanait.codeadviser.domain;

public record OpenAiChoice(OpenAiMessage message) {

    public String messageContent() {
        return message.content();
    }
}
