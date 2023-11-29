package com.DI.assignment.services;

import com.DI.assignment.Entity.Author;
import com.DI.assignment.repository.AuthorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepo authorRepo;

    public List<Author> getAllAuthors() {
        return authorRepo.findAll();
    }
    public Author addAuthor(Author author){
        if(author!=null && !author.getName().isEmpty() && author.getAddress()!=null && !author.getAddress().isBlank() ) {
            System.out.println("Author Saved!!");
            return authorRepo.save(author);
        }
        else {
            throw new IllegalArgumentException("Invalid/Incomplete details given");
        }
    }

    public List<Author> getAllAuthorsByNamesLike(String authorPattern) {
        return authorRepo.searchAuthorsByNamesLike(authorPattern);
    }
}
