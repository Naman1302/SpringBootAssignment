package com.DI.assignment.Utils;

import com.DI.assignment.DTO.AuthorDTO;
import com.DI.assignment.Entity.Author;
import org.springframework.beans.BeanUtils;

public class AuthorUtil {
    public static AuthorDTO entityToDTO (Author author){
        AuthorDTO dto=new AuthorDTO();
        BeanUtils.copyProperties(author,dto);
        return dto;
    }
    public static Author dtoToEntity(AuthorDTO dto){
        Author author=new Author();
        BeanUtils.copyProperties(dto,author);
        return author;
    }
}
