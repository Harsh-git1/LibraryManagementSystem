package com.example.librarymanagementsystem.service.java;

import com.example.librarymanagementsystem.model.java.Author;
import com.example.librarymanagementsystem.repository.java.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;
    public String addAuthor(Author author) {

        Author saveAuthor = authorRepository.save(author);
        return "Author successfully added!!";

    }
}
