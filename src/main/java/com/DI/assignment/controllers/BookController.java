package com.DI.assignment.controllers;

import com.DI.assignment.DTO.BookInsertDTO;
import com.DI.assignment.Entity.Book;
import com.DI.assignment.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;
    @GetMapping
    public List<Book> getBooks(){
        return bookService.getAllBooks();
    }
    @GetMapping("/byGenre")
    public List<Book> getBookByGenre(@RequestParam String genre){
        return bookService.getByGenre(genre);
    }
    @GetMapping("/byGenreAndCount")
    public List<Book> getBooksByGenreAndCopiesCount(@RequestParam String genre,@RequestParam int copies){
        return bookService.getByGenreAndCopiesCount(genre,copies);
    }
    @GetMapping("/byAuthorsNames")
    public List<Book> getBooksByAuthorsName(@RequestParam String authorList){
        return bookService.getBooksByAuthorsName(authorList);
    }
    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody @Validated BookInsertDTO request){
        //System.out.println(request.toString());
        Book book=request.getBook();
        String authorName=request.getAuthorName();
        Book savedBook=bookService.saveBook(book,authorName);
        return ResponseEntity.ok(savedBook);
    }
}
