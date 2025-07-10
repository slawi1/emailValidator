package com.example.emailValidator.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

@Component
public class TempDomainChecker {

    private final Set<String> tempDomains = new HashSet<>();

    @PostConstruct
    public void init() throws IOException {
        try {
            ObjectMapper mapper = new ObjectMapper();
            InputStream inputStream = getClass().getResourceAsStream("/temporary_emails.json");

            if(inputStream == null) {
                System.out.println("\uD83D\uDEA8 JSON file not found!");
            }

            if (inputStream != null) {
                Set<String> domainsFromFile = mapper.readValue(inputStream, new TypeReference<>() {});
                for (String domain : domainsFromFile) {
                    tempDomains.add(domain.toLowerCase());
                }
            }
            System.out.println("✅ Loaded domains: " + tempDomains.size());
        } catch (Exception e) {
            System.err.println("Error reading temporary_emails.json" + e.getMessage());
        }
    }

    public boolean isTemporary(String domain) {
        return tempDomains.contains(domain);
    }

}
