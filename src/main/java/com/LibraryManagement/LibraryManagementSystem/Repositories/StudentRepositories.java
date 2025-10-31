package com.LibraryManagement.LibraryManagementSystem.Repositories;

import com.LibraryManagement.LibraryManagementSystem.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepositories extends JpaRepository<StudentEntity, Long> {
}
