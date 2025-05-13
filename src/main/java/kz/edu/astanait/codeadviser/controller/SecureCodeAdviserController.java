package kz.edu.astanait.codeadviser.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.edu.astanait.codeadviser.config.OpenApiConfig;
import kz.edu.astanait.codeadviser.service.impl.SecureCodeAdviser;
import kz.edu.astanait.codeadviser.service.impl.TokenReader;
import kz.edu.astanait.codeadviser.utils.TokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Code")
@RestController
@RequiredArgsConstructor
public class SecureCodeAdviserController {

    private static final String UNAUTHORIZED_MESSAGE = "There is no matching API key.";

    private final SecureCodeAdviser secureCodeAdviser;
    private final TokenReader tokenReader;

    @Operation(
            summary = "Secure code from vulnerabilities",
            security = @SecurityRequirement(name = OpenApiConfig.SECURITY_SCHEME_NAME)
    )
    @PostMapping(value = "/api/v1/code-advice", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> secureCode(@RequestBody
                                             final String code,
                                             @Parameter(hidden = true)
                                             @RequestHeader(name = "Authorization", required = false)
                                             final String authHeader) {
        return TokenUtils.extractTokenFromHeaders(authHeader)
                .filter(tokenReader::exists)
                .map(token -> ResponseEntity.ok(secureCodeAdviser.askForAdvice(code)))
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(UNAUTHORIZED_MESSAGE));
    }
}
