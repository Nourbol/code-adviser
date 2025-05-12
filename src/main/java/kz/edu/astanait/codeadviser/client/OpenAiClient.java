package kz.edu.astanait.codeadviser.client;

import feign.RequestInterceptor;
import kz.edu.astanait.codeadviser.domain.OpenAiSendPromptRequest;
import kz.edu.astanait.codeadviser.domain.OpenAiSendPromptResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "open-ai", configuration = OpenAiClient.Configuration.class)
public interface OpenAiClient {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    OpenAiSendPromptResponse sendPrompt(OpenAiSendPromptRequest request);

    class Configuration {

        @Bean
        public RequestInterceptor authorizationRequestInterceptor(final @Value("${code-adviser.open-ai.token}") String token) {
            return requestTemplate -> requestTemplate.header(HttpHeaders.AUTHORIZATION, "Bearer %s".formatted(token));
        }
    }
}
