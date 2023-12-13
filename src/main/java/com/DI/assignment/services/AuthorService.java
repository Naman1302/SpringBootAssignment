package com.DI.assignment.services;

import com.DI.assignment.DTO.AuthorDTO;
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
        return authorRepo.findAll().stream().map(AuthorUtil::entityToDTO).toList();
    }

    public AuthorDTO addAuthor(AuthorDTO authorDTO) {
        return AuthorUtil.entityToDTO(authorRepo.save(AuthorUtil.dtoToEntity(authorDTO)));
    }

    public List<AuthorDTO> getAllAuthorsByNamesLike(String authorPattern) {
        return authorRepo.searchAuthorsByNamesLike(authorPattern).stream().map(AuthorUtil::entityToDTO).collect(Collectors.toList());
    }
}
