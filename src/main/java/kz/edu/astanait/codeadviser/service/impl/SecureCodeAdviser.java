package kz.edu.astanait.qarzhytracker.service.impl;

import kz.edu.astanait.qarzhytracker.service.PromptSender;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SecureCodeAdviser {

    private final PromptSender promptSender;
    private final SecureCodePromptBuilder promptBuilder;

    public String askForAdvice(final String vulnerableCode) {
        var prompt = promptBuilder.build(vulnerableCode);
        return promptSender.sendPrompt(prompt);
    }
}
