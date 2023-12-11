package com.DI.assignment.controllers;

import com.DI.assignment.DTO.AuthorDTO;
import com.DI.assignment.Entity.Author;
import com.DI.assignment.Utils.AuthorUtil;
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
    public List<AuthorDTO> getAllAuthors(){
        return authorService.getAllAuthors();
    }
    @GetMapping("/byNamesLike")
    public List<AuthorDTO> getAllByNamesLike(@RequestParam String authorPattern) {
        return authorService.getAllAuthorsByNamesLike(authorPattern);
    }

    @PostMapping
    public AuthorDTO addAuthor(@RequestBody AuthorDTO authorDTO){
        return authorService.addAuthor(authorDTO);
    }

}
