package com.example.librarymanagementsystem.service.java;

import com.example.librarymanagementsystem.Enum.java.TransactionStatus;
import com.example.librarymanagementsystem.dto.ResponseDto.IssueBookResponse;
import com.example.librarymanagementsystem.expection.java.BookNotFoundException;
import com.example.librarymanagementsystem.expection.java.StudentNotFoundException;
import com.example.librarymanagementsystem.model.java.Book;
import com.example.librarymanagementsystem.model.java.Student;
import com.example.librarymanagementsystem.model.java.Transaction;
import com.example.librarymanagementsystem.repository.java.BookRepository;
import com.example.librarymanagementsystem.repository.java.StudentRepository;
import com.example.librarymanagementsystem.repository.java.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;
import java.util.UUID;

@Service
public class TransactionService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    BookRepository bookRepository;
    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    TransactionRepository transactionRepository;

    public IssueBookResponse issueBook(int bookId, int studentId) {

        Optional<Student> studentOptional = studentRepository.findById(studentId);

        if(studentOptional.isEmpty()) {
            throw new StudentNotFoundException("Invalid Student Id");
        }

        Optional<Book> bookOptional = bookRepository.findById(bookId);
        if(bookOptional.isEmpty()) {
            throw new BookNotFoundException("Invalid book Id");
        }

        //now to check whether the book is already issued or not
        Book book = bookOptional.get();
        if(book.isIssued()) {
            throw new BookNotFoundException("Book already issued");
        }
        Student student = studentOptional.get();

        //create transaction
        Transaction transaction = Transaction.builder()
                .transactionNumber(String.valueOf(UUID.randomUUID()))
                .transactionStatus(TransactionStatus.Successful)
                .book(book)
                .libraryCard(student.getLibraryCard())
                .build();

        Transaction savedTransaction = transactionRepository.save(transaction);
        book.getTransactions().add(savedTransaction);
        book.setIssued(true);
        book.getTransactions().add(savedTransaction);


        student.getLibraryCard().getTransactions().add(savedTransaction);
        //save book and student
        Book savedBook = bookRepository.save(book);
        Student savedStudent = studentRepository.save(student);

        String text = "Dear " + student.getName() + ", you have successfully issued a book\n" +
                "The transaction details are given below :" +
                "\nTransaction number : " + savedTransaction.getTransactionNumber() +
                "\nBook title : " + savedBook.getTitle() +
                "\nAuthor : " + savedBook.getAuthor() +
                "\nIssue Date and Time : " + savedTransaction.getTransactionTime();

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("demoperson206@gmail.com");
        simpleMailMessage.setTo(savedStudent.getEmail());
        simpleMailMessage.setSubject("Congrats!! Book Issued");
        simpleMailMessage.setText(text);
        javaMailSender.send(simpleMailMessage);



        return IssueBookResponse.builder()
                .bookName(book.getTitle())
                .authorName(book.getAuthor().getName())
                .libraryCardNumber(student.getLibraryCard().getCardNo())
                .studentName(student.getName())
                .transactionNumber(savedTransaction.getTransactionNumber())
                .transactionTime(savedTransaction.getTransactionTime())
                .transactionStatus(savedTransaction.getTransactionStatus())
                .build();
    }
}
