package kz.edu.astanait.codeadviser.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "code-adviser.open-ai.default-settings")
public record OpenAiDefaultSettingsConfigProperties(String model,
                                                    String role,
                                                    int n,
                                                    double temperature) {
}
