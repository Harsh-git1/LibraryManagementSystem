package com.example.librarymanagementsystem.dto.ResponseDto;

import com.example.librarymanagementsystem.Enum.java.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class StudentResponse {

    String name;
    String email;
    //String message;
    String cardIssuedNo;

}
