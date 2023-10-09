package com.example.librarymanagementsystem.expection.java;
    public class BookNotFoundException extends RuntimeException {
        public BookNotFoundException(String messaage) {
            super(messaage);
        }
    }
