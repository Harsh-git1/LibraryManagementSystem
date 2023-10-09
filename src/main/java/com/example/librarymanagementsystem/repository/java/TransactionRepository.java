package com.example.librarymanagementsystem.repository.java;

import com.example.librarymanagementsystem.model.java.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
}
