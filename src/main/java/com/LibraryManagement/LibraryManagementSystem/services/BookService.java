package com.LibraryManagement.LibraryManagementSystem.services;

import com.LibraryManagement.LibraryManagementSystem.Repositories.BookRepository;
import com.LibraryManagement.LibraryManagementSystem.dto.BookDto;
import com.LibraryManagement.LibraryManagementSystem.entity.Book;
import org.modelmapper.ModelMapper;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;

    public BookService(BookRepository bookRepository, ModelMapper modelMapper) {
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;
    }

    public BookDto addNewBook(BookDto bookDto){
        if(bookRepository.existsByTitle(bookDto.getTitle())){
            throw new RuntimeException("Book with the same title already exists");
        }
        Book toSaveEntity = modelMapper.map(bookDto, Book.class);
        Book savedEntity = bookRepository.save(toSaveEntity);
        return modelMapper.map(savedEntity, BookDto.class);
    }

    public List<BookDto> getAllBooks(){
        List<Book> books = bookRepository.findAll();
        return books.stream()
                .map(book -> modelMapper.map(book, BookDto.class))
                .toList();
    }

    public BookDto getBookById(Long id){
        Book bookEntity = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book not found"));
        return modelMapper.map(bookEntity, BookDto.class);
    }


}
