package com.example.nevena.internship.service.exception;

public class CredentialsInvalidException extends Exception {
    public CredentialsInvalidException() {
        super("Credentials are invalid!");
    }
}
