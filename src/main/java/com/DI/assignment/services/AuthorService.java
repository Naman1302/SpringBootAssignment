package com.DI.assignment.services;

import com.DI.assignment.DTO.AuthorDTO;
import com.DI.assignment.Entity.Author;
import com.DI.assignment.Utils.AuthorUtil;
import com.DI.assignment.repository.AuthorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepo authorRepo;

    public List<AuthorDTO> getAllAuthors() {
        List<Author> authors= authorRepo.findAll();
        return authors.stream().map(AuthorUtil::entityToDTO).collect(Collectors.toList());
    }
    public AuthorDTO addAuthor(AuthorDTO authorDTO){
        Author author= authorRepo.save(AuthorUtil.dtoToEntity(authorDTO));
        return AuthorUtil.entityToDTO(author);
    }

    public List<AuthorDTO> getAllAuthorsByNamesLike(String authorPattern) {
        List<Author> authors=authorRepo.searchAuthorsByNamesLike(authorPattern);
        return authors.stream().map(AuthorUtil::entityToDTO).collect(Collectors.toList());
    }
}
