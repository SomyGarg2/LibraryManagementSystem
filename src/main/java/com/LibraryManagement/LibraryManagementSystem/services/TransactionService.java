package com.LibraryManagement.LibraryManagementSystem.services;

import com.LibraryManagement.LibraryManagementSystem.Repositories.BookRepository;
import com.LibraryManagement.LibraryManagementSystem.Repositories.StudentRepository;
import com.LibraryManagement.LibraryManagementSystem.Repositories.TransactionRepository;
import com.LibraryManagement.LibraryManagementSystem.dto.TransactionDto;
import com.LibraryManagement.LibraryManagementSystem.entity.Book;
import com.LibraryManagement.LibraryManagementSystem.entity.Student;
import com.LibraryManagement.LibraryManagementSystem.entity.Transaction;
import org.modelmapper.ModelMapper;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TransactionService {


    private final TransactionRepository transactionRepository;
    private final ModelMapper modelMapper;
    private final StudentRepository studentRepository;
    private final BookRepository bookRepository;

    public TransactionService(TransactionRepository transactionRepository, ModelMapper modelMapper,
                              StudentRepository studentRepository, BookRepository bookRepository) {
        this.transactionRepository = transactionRepository;
        this.modelMapper = modelMapper;
        this.studentRepository = studentRepository;
        this.bookRepository = bookRepository;

    }

    private Student getStudentOrThrow(Long studentId) {
        return studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
    }

    private Book getBookOrThrow(Long bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));
    }

    public boolean isBookAvailable(Long bookId) {
        Book bookEntity = getBookOrThrow(bookId);
        return bookEntity.getStock() > 0;
    }

    private TransactionDto createTransaction(Long studentId, Long bookId, String type, Double amount){
        Transaction transaction = new Transaction();
        transaction.setStudentId(studentId);
        transaction.setBookId(bookId);
        transaction.setType(type);
        transaction.setAmount(amount);
        transaction.setDate(LocalDateTime.now());
        transactionRepository.save(transaction);
        return modelMapper.map(transaction, TransactionDto.class);
    }

    public TransactionDto buyBook(Long studentId, Long bookId){
        Student studentEntity = getStudentOrThrow(studentId);
        Book bookEntity = getBookOrThrow(bookId);
        if (!isBookAvailable(bookId)){
            throw new ResourceNotFoundException("Book is out of stock");
        }

        if (studentEntity.getWalletBalance() < bookEntity.getPrice()){
            throw new ResourceNotFoundException("insufficient balance");
        }

        studentEntity.setWalletBalance(studentEntity.getWalletBalance() - bookEntity.getPrice());
        bookEntity.setStock(bookEntity.getStock() - 1);

        studentRepository.save(studentEntity);
        bookRepository.save(bookEntity);

        // Create and save transaction
        return createTransaction(studentId, bookId, "BUY", bookEntity.getPrice());
    }


    public TransactionDto rentBook(Long studentId, Long bookId){
        Student studentEntity = getStudentOrThrow(studentId);
        Book bookEntity = getBookOrThrow(bookId);
        if (!isBookAvailable(bookId)){
            throw new ResourceNotFoundException("Book is out of stock");
        }

        if (studentEntity.getWalletBalance() < bookEntity.getRentPrice()){
            throw new ResourceNotFoundException("insufficient balance");
        }

        studentEntity.setWalletBalance(studentEntity.getWalletBalance() - bookEntity.getRentPrice());
        bookEntity.setStock(bookEntity.getStock() - 1);

        studentRepository.save(studentEntity);
        bookRepository.save(bookEntity);

       return createTransaction(studentId, bookId, "RENT", bookEntity.getRentPrice());

    }


    public TransactionDto returnRentedBook(Long studentId, Long bookId){
        Student studentEntity = getStudentOrThrow(studentId);
        Book bookEntity = getBookOrThrow(bookId);

        bookEntity.setStock(bookEntity.getStock() + 1);
        bookRepository.save(bookEntity);

        return createTransaction(studentId, bookId, "RETURN", 0.0);
    }


}
