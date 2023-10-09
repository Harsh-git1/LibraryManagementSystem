package com.example.librarymanagementsystem.service.java;

import com.example.librarymanagementsystem.Enum.java.Genre;
import com.example.librarymanagementsystem.dto.ResponseDto.BookResponse;
import com.example.librarymanagementsystem.dto.requestDto.BookRequest;
import com.example.librarymanagementsystem.expection.java.AuthorNotFoundException;
import com.example.librarymanagementsystem.model.java.Author;
import com.example.librarymanagementsystem.model.java.Book;
import com.example.librarymanagementsystem.repository.java.AuthorRepository;
import com.example.librarymanagementsystem.repository.java.BookRepository;
import com.example.librarymanagementsystem.transformer.BookTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    BookRepository bookRepository;
    public String addBook(BookRequest bookRequest) {

        Optional<Author> optionalAuthor = authorRepository.findById(bookRequest.getAuthorId());

        if(optionalAuthor.isEmpty()) {
            throw new AuthorNotFoundException("Invalid author Id!!");
        }

        Author author = optionalAuthor.get();

        Book book = Book.builder()
                .author(author)
                .cost(bookRequest.getCost())
                .genre(bookRequest.getGenre())
                .noOfPages(bookRequest.getNoOfPages())
                .title(bookRequest.getTitle())
                .build();


        author.getBooks().add(book);
        authorRepository.save(author);
        return "Book added successfully!!";
    }

    public List<BookResponse> getBooksByGenreAndCostGreaterThan(String genre, double cost) {

        List<Book> books = bookRepository.getBooksByGenreAndCostGreaterThan(genre,cost);

        //prepare the response. convert model to dto
        List<BookResponse> response = new ArrayList<>();
        for(Book book: books){
            response.add(BookTransformer.BookToBookResponse(book));
        }
        return response;

    }

    public List<BookResponse> getBooksByGenreAndCostGreaterThanHQL(Genre genre, double cost) {

        List<Book> books = bookRepository.getBooksByGenreAndCostGreaterThanHQL(genre,cost);
        //prepare the response. convert model to dto
        List<BookResponse> response = new ArrayList<>();
        for(Book book: books){
            response.add(BookTransformer.BookToBookResponse(book));
        }
        return response;
    }
}
