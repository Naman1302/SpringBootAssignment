package com.DI.assignment.controllers;

import com.DI.assignment.DTO.BookDTO;
import com.DI.assignment.DTO.BookInsertDTO;
import com.DI.assignment.Utils.BookUtil;
import com.DI.assignment.services.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;
    @GetMapping
    public List<BookDTO> getBooks(){
        return bookService.getAllBooks();
    }
    @GetMapping("/byGenre")
    public List<BookDTO> getBookByGenre(@RequestParam String genre){
        return bookService.getByGenre(genre);
    }
    @GetMapping("/byGenreAndCount")
    public List<BookDTO> getBooksByGenreAndCopiesCount(@RequestParam String genre,@RequestParam int copies){
        return bookService.getByGenreAndCopiesCount(genre,copies);
    }
    @GetMapping("/byAuthorsNames")
    public List<BookDTO> getBooksByAuthorsName(@RequestParam String authorList){
        return bookService.getBooksByAuthorsName(authorList);
    }
    @GetMapping("byAuthorNameByFlux")
    public List<BookDTO> getBooksByAuthorsNamesByFlux(@RequestParam String authorList){
        return bookService.getBooksByAuthorsNameByFlux(authorList);

    }
    @GetMapping("/getBookByIds")
    public List<BookDTO> getBooksByIds(@RequestParam String bookIdList){
        return bookService.getBooksByIds(bookIdList);
    }
    @PostMapping
    public ResponseEntity<BookDTO> addBook(@Valid @RequestBody  BookInsertDTO request){
        //System.out.println(request.toString());
        BookDTO bookDTO= BookUtil.entityToDTO(request.getBook());
        String authorName=request.getAuthorName();
        BookDTO savedBook=bookService.saveBook(bookDTO,authorName);
        return ResponseEntity.ok(savedBook);
    }
}
