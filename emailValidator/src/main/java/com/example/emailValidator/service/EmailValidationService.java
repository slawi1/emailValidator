package com.example.emailValidator.service;

import com.example.emailValidator.model.EmailValidationResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Service;

import javax.naming.NameNotFoundException;
import javax.naming.ServiceUnavailableException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import java.util.Hashtable;

@Service
@Slf4j
public class EmailValidationService {

    private final TempDomainChecker tempDomainChecker;

    public EmailValidationService(TempDomainChecker tempDomainChecker) {
        this.tempDomainChecker = tempDomainChecker;
    }

    String user;
    String domain;

    public EmailValidationResponse validate(String email) {
        boolean validSyntax = isValidEmailFormat(email);
        boolean hasMxRecord = false;
        boolean temporaryDomain = false;


        if (validSyntax) {
            user = email.split("@")[0];
            domain = email.substring(email.indexOf("@") + 1).toLowerCase();
            hasMxRecord = hasMxRecord(domain);
            temporaryDomain = tempDomainChecker.isTemporary(domain);
        }

        return new EmailValidationResponse(email, user, domain, validSyntax, hasMxRecord, temporaryDomain);

    }

    private boolean isValidEmailFormat(String email) {
        return EmailValidator.getInstance().isValid(email);
    }

    private boolean hasMxRecord(String domain) {

        try {
            Hashtable<String, String> env = new Hashtable<>();
            env.put("java.naming.factory.initial", "com.sun.jndi.dns.DnsContextFactory");
            DirContext context = new InitialDirContext(env);
            Attributes attributes = context.getAttributes(domain, new String[]{"MX"});
            Attribute attribute = attributes.get("MX");
            return attribute != null;
        } catch (NameNotFoundException | ServiceUnavailableException e) {
            log.warn("DNS not found for domain: '{}' {}", domain, e.getMessage());
            return false;
        } catch (Exception e) {
            log.warn("An unexpected DNS error: {}", e.getMessage());
            return false;
        }
    }
}