package com.DI.assignment.controllers;

import com.DI.assignment.Entity.Author;
import com.DI.assignment.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class Authorcontroller {
    @Autowired
    public AuthorService authorService;

    @GetMapping
    public List<Author> getAllAuthors(){
        return authorService.getAllAuthors();
    }
    @GetMapping("/byNamesLike")
    public List<Author> getAllByNamesLike(@RequestParam String authorPattern) {
        return authorService.getAllAuthorsByNamesLike(authorPattern);
    }

    @PostMapping
    public Author addAuthor(@RequestBody Author author){
        return authorService.addAuthor(author);
    }

}
