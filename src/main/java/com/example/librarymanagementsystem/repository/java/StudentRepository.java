package com.example.librarymanagementsystem.repository.java;

import com.example.librarymanagementsystem.Enum.java.Gender;
import com.example.librarymanagementsystem.model.java.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    List<Student> findByGender(Gender gender);
    Student findByEmail(String email);
    Student findByGenderAndEmail(Gender gender, String email);
}
