package kz.edu.astanait.codeadviser.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SecureCodePromptBuilder {

    private static final String PROMPT = """
        You are a security code reviewer. Analyze the following Git diff for potential security vulnerabilities, insecure coding practices, or anti-patterns. For each issue you find, follow this structure:

        File and Line Reference: Specify the file name and line number(s) affected.
        Issue Summary: Briefly describe the problem.
        Risk Level: Low / Medium / High.
        Explanation: Why this is a security concern.
        Recommendation: How to fix or improve the code securely.
        CWE Tag (if applicable): Common Weakness Enumeration ID and name (e.g., CWE-89: SQL Injection).

        Be concise but accurate. Do not comment on style or formatting unless it introduces a security risk. Focus only on what has changed in the diff. If there are no issues, respond: "✅ No security vulnerabilities found in the provided diff."

        Here is the diff:
        ```
        %s
        ```
        """;

    public String build(final String vulnerableCode) {
        return PROMPT.formatted(vulnerableCode);
    }
}
