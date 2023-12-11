package com.DI.assignment;

import com.DI.assignment.DTO.AuthorDTO;
import com.DI.assignment.Entity.Address;
import com.DI.assignment.Entity.Author;
import com.DI.assignment.Utils.AuthorUtil;
import com.DI.assignment.repository.AuthorRepo;
import com.DI.assignment.services.AuthorService;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class AuthorServiceTests {
    @InjectMocks
    private AuthorService authorService;
    @Mock
    private AuthorRepo authorRepo;

    private final ObjectId authorId1=new ObjectId(),authorId2=new ObjectId();
    @Test
    public void getAllAuthorsTest(){
        AuthorDTO a1=new AuthorDTO();
        a1.setName("Ram");
        a1.setAddress(new Address(2,"Kali","Cutta"));
        a1.setId(authorId1);

        AuthorDTO a2=new AuthorDTO();
        a1.setName("Shyam");
        a1.setAddress(new Address(4,"Juhu","Maharashtra"));
        a1.setId(authorId2);

        List<AuthorDTO> authorList=List.of(a1,a2);

        when(authorRepo.findAll()).thenReturn(authorList);

        List<AuthorDTO> result=authorService.getAllAuthors();

        assertNotNull(result);
        assertEquals(2,result.size());
        verify(authorRepo,times(1)).findAll();
    }
    @Test
    public void addAuthorTest(){
        Author testAuthor=new Author();
        testAuthor.setid(authorId1);
        testAuthor.setName("Ram");
        testAuthor.setAddress(new Address(56,"Lalu","Mongol"));

        when(authorRepo.save(testAuthor)).thenReturn(testAuthor);
        AuthorDTO dto=AuthorUtil.entityToDTO(testAuthor);
        AuthorDTO result=authorService.addAuthor(dto);

        assertNotNull(result);
        assertEquals(testAuthor,result);
    }
    @Test
    public void getAllAuthorsByNamesLikeTest(){
        String authorPattern="Ra";

        AuthorDTO a1=new AuthorDTO(authorId1,"Ravan",new Address(45,"Lanka","Matara"));
        AuthorDTO a2=new AuthorDTO(authorId2,"Vibhishan",new Address(46,"Lanka","Matara"));

        List<AuthorDTO> expectedAuthors= List.of(a1);

        when(authorRepo.save(AuthorUtil.dtoToEntity(a1))).thenReturn(a1);
        when(authorRepo.save(AuthorUtil.dtoToEntity(a2))).thenReturn(a2);

        when(authorRepo.searchAuthorsByNamesLike(authorPattern)).thenReturn(expectedAuthors);

        List<AuthorDTO> result=authorService.getAllAuthorsByNamesLike(authorPattern);

        assertNotNull(result);
        assertEquals(1,result.size());
        assertEquals(expectedAuthors,result);
        verify(authorRepo,times(1)).searchAuthorsByNamesLike(anyString());
    }
}
