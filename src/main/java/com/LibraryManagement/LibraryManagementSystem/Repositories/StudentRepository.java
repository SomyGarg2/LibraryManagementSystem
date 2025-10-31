package com.LibraryManagement.LibraryManagementSystem.Repositories;

import com.LibraryManagement.LibraryManagementSystem.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
