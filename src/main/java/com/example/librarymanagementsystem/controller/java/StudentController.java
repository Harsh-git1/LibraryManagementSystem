package com.example.librarymanagementsystem.controller.java;

import com.example.librarymanagementsystem.dto.ResponseDto.StudentResponse;
import com.example.librarymanagementsystem.dto.requestDto.StudentRequest;
import com.example.librarymanagementsystem.service.java.StudentService;
import com.example.librarymanagementsystem.model.java.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentService studentService;

    @PostMapping("/add")
    public ResponseEntity addStudent(@RequestBody StudentRequest studentRequest) {
        StudentResponse response = studentService.addStudent(studentRequest);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @GetMapping("/get")
    public ResponseEntity getStudent(@RequestParam("id") int regNo) {
        StudentResponse response = studentService.getStudent(regNo);
        if(response == null) {
            return new ResponseEntity("Invalid id..!!" , HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(response, HttpStatus.FOUND);
    }

    //update a age of student
    @PutMapping("/update-age")
    public ResponseEntity updateAge(@RequestParam int regNo, @RequestParam int age) {
        StudentResponse studentResponse = studentService.updateAge(regNo, age);
        if(studentResponse == null) {
            return new ResponseEntity("Invalid id..!!", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(studentResponse, HttpStatus.OK);
    }

    //delete student with given regNo
    @DeleteMapping("/delete")
    public ResponseEntity deleteStudent(@RequestParam int regNo) {
        String status = studentService.deleteStudent(regNo);

        if(status != null) {
            return new ResponseEntity(status, HttpStatus.OK);
        }
        return new ResponseEntity("regNo is invalid..!!", HttpStatus.BAD_REQUEST);
    }


    //get all the student in the db
    @GetMapping("/get-all-student")
    public ResponseEntity getAllStudent(){
        List<Student> students = studentService.getAllStudent();
        return new ResponseEntity(students, HttpStatus.FOUND);
    }


    //get list of all male student
    @GetMapping("/get-male-student")
    public ResponseEntity getMaleStudent(){
        List<Student> students = studentService.getMaleStudent();
        return new ResponseEntity(students, HttpStatus.FOUND);
    }

}
