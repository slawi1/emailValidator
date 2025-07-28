package com.example.emailValidator.controller;

import com.example.emailValidator.model.EmailValidationResponse;
import com.example.emailValidator.service.EmailValidationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailValidatorController {

    @Value("${RAPIDAPI_PROXY_SECRET}")
    private String rapidApiSecret;

    private final EmailValidationService emailValidationService;

    public EmailValidatorController(EmailValidationService emailValidationService) {
        this.emailValidationService = emailValidationService;
    }
    @GetMapping("/")
    public ResponseEntity<String> root() {
        return ResponseEntity.ok("Email Validator API is working.");
    }

    @GetMapping("/api/v1/validate")
    public ResponseEntity<EmailValidationResponse> validateEmail(@RequestParam String email
            , @RequestHeader(value = "X-RapidAPI-Proxy-Secret", required = false) String proxySecret) {

        if (proxySecret == null || !proxySecret.equals(rapidApiSecret)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        EmailValidationResponse response = emailValidationService.validate(email);
        return ResponseEntity.ok(response);
    }

}
