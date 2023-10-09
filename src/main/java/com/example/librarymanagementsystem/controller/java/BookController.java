package com.example.librarymanagementsystem.controller.java;

import com.example.librarymanagementsystem.Enum.java.Genre;
import com.example.librarymanagementsystem.dto.ResponseDto.BookResponse;
import com.example.librarymanagementsystem.dto.requestDto.BookRequest;
import com.example.librarymanagementsystem.expection.java.AuthorNotFoundException;
import com.example.librarymanagementsystem.model.java.Book;
import com.example.librarymanagementsystem.service.java.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping("/add")
    public String addBook (@RequestBody BookRequest bookRequest) {
        try{
            String response = bookService.addBook(bookRequest);
            return response;
        }
        catch (Exception e){
            return e.getMessage();
        }
    }

    // delete a book

    // give me names of all the books of a particular genre

    // give me names of all the books of a particular genre and cost gretaer than x rs

    @GetMapping("/get-by-genre-cost")
    public List<BookResponse> getBooksByGenreAndCostGreaterThan(@RequestParam("genre") String genre,
                                                                @RequestParam("cost") double cost){
        return bookService.getBooksByGenreAndCostGreaterThan(genre,cost);

    }

    @GetMapping("/get-by-genre-cost-hql")
    public List<BookResponse> getBooksByGenreAndCostGreaterThanHQL(@RequestParam("genre") Genre genre,
                                                                   @RequestParam("cost") double cost){
        return bookService.getBooksByGenreAndCostGreaterThanHQL(genre,cost);

    }

    // give me all the books having number of pages between 'a' and 'b'

    // give me the names of all the authors who write a particular genre

}
