package com.LibraryManagement.LibraryManagementSystem.Repositories;

import com.LibraryManagement.LibraryManagementSystem.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepositories extends JpaRepository<TransactionEntity, Long> {
}
