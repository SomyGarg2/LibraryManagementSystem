package com.LibraryManagement.LibraryManagementSystem.Repositories;

import com.LibraryManagement.LibraryManagementSystem.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByStudentId(Long studentId);
}
