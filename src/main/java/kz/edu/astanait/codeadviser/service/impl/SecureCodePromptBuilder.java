package kz.edu.astanait.qarzhytracker.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SecureCodePromptBuilder {

    private static final String PROMPT = """
        Act as static code analyzer, your aim to suggest a secure version of the provided code.
        As an input I give you code that has potentially vulnerable areas.
        As an output I expect the secure version of the provided code.
        Input:
        ```
        %s
        ```

        Output:
        """;

    public String build(final String vulnerableCode) {
        return PROMPT.formatted(vulnerableCode);
    }
}
