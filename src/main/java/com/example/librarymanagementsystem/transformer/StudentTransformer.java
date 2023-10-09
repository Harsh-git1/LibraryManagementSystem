package com.example.librarymanagementsystem.transformer;

import com.example.librarymanagementsystem.dto.ResponseDto.StudentResponse;
import com.example.librarymanagementsystem.dto.requestDto.StudentRequest;
import com.example.librarymanagementsystem.model.java.Student;

public class StudentTransformer {

    public static Student studentRequestToStudent(StudentRequest studentRequest) {
        return Student.builder()
                .name(studentRequest.getName())
                .age(studentRequest.getAge())
                .email(studentRequest.getEmail())
                .gender(studentRequest.getGender())
                .build();

    }

    public static StudentResponse studentToStudentResponse(Student student) {
        return StudentResponse.builder()
                .email(student.getEmail())
                .name(student.getName())
                .cardIssuedNo(student.getLibraryCard().getCardNo())
                .build();
    }
}
