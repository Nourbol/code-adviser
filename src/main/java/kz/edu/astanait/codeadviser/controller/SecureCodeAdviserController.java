package kz.edu.astanait.codeadviser.controller;

import kz.edu.astanait.codeadviser.service.impl.SecureCodeAdviser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SecureCodeAdviserController {

    private final SecureCodeAdviser secureCodeAdviser;

    @PostMapping(value = "/api/v1/code-advice", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public String secureCode(@RequestBody String code) {
        return secureCodeAdviser.askForAdvice(code);
    }
}
