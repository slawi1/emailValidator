package com.example.emailValidator.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailValidationResponse {

    private String email;

    private String user;

    private String domain;

    private boolean validSyntax;

    private boolean hasMxRecord;

    private boolean isTemporary;


}
