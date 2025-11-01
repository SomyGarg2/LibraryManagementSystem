package com.LibraryManagement.LibraryManagementSystem.Controllers;

import com.LibraryManagement.LibraryManagementSystem.dto.BookDto;
import com.LibraryManagement.LibraryManagementSystem.services.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public BookDto addNewBook(@RequestBody BookDto bookDto){
        return bookService.addNewBook(bookDto);
    }

    @GetMapping
    public List<BookDto> getAllBooks(){
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public BookDto getBookById(@PathVariable Long id){
        return bookService.getBookById(id);
    }
}
