package com.example.librarymanagementsystem.dto.requestDto;

import com.example.librarymanagementsystem.Enum.java.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Builder
public class StudentRequest {

    String name;
    int age;
    String email;
    Gender gender;


}
