package com.LibraryManagement.LibraryManagementSystem.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class TransactionDto {

    private Long id;

    private Long studentId;

    private Long bookId;

    private String type;

    private Double amount;

    private LocalDateTime date;
}
