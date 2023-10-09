package com.example.librarymanagementsystem.transformer;

import com.example.librarymanagementsystem.dto.ResponseDto.BookResponse;
import com.example.librarymanagementsystem.model.java.Book;

public class BookTransformer {

    public static BookResponse BookToBookResponse(Book book){

        return BookResponse.builder()
                .authorName(book.getAuthor().getName())
                .cost(book.getCost())
                .genre(book.getGenre())
                .noOfPages(book.getNoOfPages())
                .title(book.getTitle())
                .build();
    }
}
