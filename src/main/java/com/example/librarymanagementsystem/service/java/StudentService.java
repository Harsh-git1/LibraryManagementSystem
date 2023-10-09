package com.example.librarymanagementsystem.service.java;

import com.example.librarymanagementsystem.Enum.java.CardStatus;
import com.example.librarymanagementsystem.Enum.java.Gender;
import com.example.librarymanagementsystem.dto.ResponseDto.StudentResponse;
import com.example.librarymanagementsystem.dto.requestDto.StudentRequest;
import com.example.librarymanagementsystem.model.java.Author;
import com.example.librarymanagementsystem.model.java.LibraryCard;
import com.example.librarymanagementsystem.model.java.Student;
import com.example.librarymanagementsystem.repository.java.StudentRepository;
import com.example.librarymanagementsystem.transformer.StudentTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;
    public StudentResponse addStudent(StudentRequest studentRequest) {
          //normal
//        Student student = new Student();
//        student.setName(studentRequest.getName());
//        student.setEmail(studentRequest.getEmail());
//        student.setGender(studentRequest.getGender());
//        student.setAge(studentRequest.getAge());

        //using builder
//        Student student = Student.builder()
//                .name(studentRequest.getName())
//                .age(studentRequest.getAge())
//                .email(studentRequest.getEmail())
//                .gender(studentRequest.getGender())
//                .build();


        //using Transformer
        Student student = StudentTransformer.studentRequestToStudent(studentRequest);

//        LibraryCard libraryCard = new LibraryCard();
//        libraryCard.setCardNo(String.valueOf(UUID.randomUUID()));
//        libraryCard.setStudent(student);
//        libraryCard.setCardStatus(CardStatus.ACTIVE);

        LibraryCard libraryCard = LibraryCard.builder()
                .cardNo(String.valueOf(UUID.randomUUID()))
                .cardStatus(CardStatus.ACTIVE)
                .student(student)
                .build();

        student.setLibraryCard(libraryCard);
        Student savedStudent = studentRepository.save(student);
        return StudentTransformer.studentToStudentResponse(savedStudent);

    }

    public StudentResponse getStudent(int regNo) {

        Optional<Student> studentOptional = studentRepository.findById(regNo);
        if(studentOptional.isEmpty()) return null;
        Student student = studentOptional.get();
        return StudentTransformer.studentToStudentResponse(student);

    }

    public StudentResponse updateAge(int regNo, int age) {
        Optional<Student> studentOptional = studentRepository.findById(regNo);

        if(studentOptional.isPresent()) {
            Student student = studentOptional.get();
            student.setAge(age);
            studentRepository.save(student);
            return StudentTransformer.studentToStudentResponse(student);
        }
        return null;
    }

    public String deleteStudent(int regNo) {
        Optional<Student> studentOptional = studentRepository.findById(regNo);
        if(studentOptional.isPresent()) {
            studentRepository.delete(studentOptional.get());
            return "Successfully deleted student";
        }
        return null;
    }

    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    public List<Student> getMaleStudent() {
        List<Student> students = studentRepository.findAll();
        List<Student> maleStudents = new ArrayList<>();

        for(Student student : students) {
            if(student.getGender() == Gender.male) {
                maleStudents.add(student);
            }
        }

        return maleStudents;
    }


}
