package kz.edu.astanait.codeadviser;

import kz.edu.astanait.codeadviser.config.OpenAiDefaultSettingsConfigProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableConfigurationProperties({
        OpenAiDefaultSettingsConfigProperties.class
})
@SpringBootApplication
public class CodeAdviserApplication {

    public static void main(String[] args) {
        SpringApplication.run(CodeAdviserApplication.class, args);
    }
}
