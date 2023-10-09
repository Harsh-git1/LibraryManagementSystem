package com.example.librarymanagementsystem.expection.java;



public class StudentNotFoundException extends RuntimeException{

    public StudentNotFoundException(String message) {
        super(message);
    }
}
