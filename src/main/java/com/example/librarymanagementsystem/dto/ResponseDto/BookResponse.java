package com.example.librarymanagementsystem.dto.ResponseDto;

import com.example.librarymanagementsystem.Enum.java.Genre;
import lombok.*;
import lombok.experimental.FieldDefaults;


@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class BookResponse {

    String title;

    int noOfPages;

    Genre genre;

    double cost;

    String authorName;
}
