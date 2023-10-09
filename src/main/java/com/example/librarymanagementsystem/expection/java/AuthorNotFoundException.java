package com.example.librarymanagementsystem.expection.java;

public class AuthorNotFoundException extends RuntimeException{

    public AuthorNotFoundException(String message) {
        super(message);
    }
}
