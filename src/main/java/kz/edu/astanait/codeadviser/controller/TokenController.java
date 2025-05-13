package kz.edu.astanait.codeadviser.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.edu.astanait.codeadviser.service.GeneratedTokenSender;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Token")
@RestController
@RequiredArgsConstructor
public class TokenController {

    private final GeneratedTokenSender tokenSender;

    @Operation(summary = "Send generated token to an email")
    @PostMapping(value = "/api/v1/tokens", consumes = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<Void> sendGeneratedToken(final @RequestBody String email) {
        tokenSender.sendGeneratedToken(email);
        return ResponseEntity.noContent().build();
    }
}
