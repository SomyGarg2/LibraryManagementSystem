package com.LibraryManagement.LibraryManagementSystem.Repositories;

import com.LibraryManagement.LibraryManagementSystem.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepositories extends JpaRepository<BookEntity, Long> {
}
