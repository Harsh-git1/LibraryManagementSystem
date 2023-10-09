package com.example.librarymanagementsystem.repository.java;

import com.example.librarymanagementsystem.model.java.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {


}
