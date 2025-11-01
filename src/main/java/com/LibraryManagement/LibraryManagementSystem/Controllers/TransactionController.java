package com.LibraryManagement.LibraryManagementSystem.Controllers;

import com.LibraryManagement.LibraryManagementSystem.dto.TransactionDto;
import com.LibraryManagement.LibraryManagementSystem.services.TransactionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/buy")
    public TransactionDto buyBook(@RequestParam Long studentId, @RequestParam Long bookId) {
        return transactionService.buyBook(studentId, bookId);
    }

    @PostMapping("/rent")
    public TransactionDto rentBook(@RequestParam Long studentId, @RequestParam Long bookId) {
        return transactionService.rentBook(studentId, bookId);
    }

    @PostMapping("/return")
    public TransactionDto returnRentedBook(@RequestParam Long studentId, @RequestParam Long bookId) {
        return transactionService.returnRentedBook(studentId, bookId);
    }
}