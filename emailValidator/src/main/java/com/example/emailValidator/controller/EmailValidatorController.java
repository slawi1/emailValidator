package com.example.emailValidator.controller;

import com.example.emailValidator.model.EmailValidationResponse;
import com.example.emailValidator.service.EmailValidationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/email")
public class EmailValidatorController {

    private final EmailValidationService emailValidationService;

    public EmailValidatorController(EmailValidationService emailValidationService) {
        this.emailValidationService = emailValidationService;
    }

    @GetMapping("/validate")
    public ResponseEntity<EmailValidationResponse> validateEmail(@RequestParam String email) {
        EmailValidationResponse response = emailValidationService.validate(email);
        return ResponseEntity.ok(response);
    }

}
