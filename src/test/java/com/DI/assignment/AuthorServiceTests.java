package com.DI.assignment;

import com.DI.assignment.Entity.Address;
import com.DI.assignment.Entity.Author;
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
        Author a1=new Author();
        a1.setName("Ram");
        a1.setAddress(new Address(2,"Kali","Cutta"));
        a1.setid(authorId1);

        Author a2=new Author();
        a1.setName("Shyam");
        a1.setAddress(new Address(4,"Juhu","Maharashtra"));
        a1.setid(authorId2);

        List<Author> authorList=List.of(a1,a2);

        when(authorRepo.findAll()).thenReturn(authorList);

        List<Author> result=authorService.getAllAuthors();

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

        Author result=authorService.addAuthor(testAuthor);

        assertNotNull(result);
        assertEquals(testAuthor,result);
    }
    @Test
    public void getAllAuthorsByNamesLikeTest(){
        String authorPattern="Ra";

        Author a1=new Author(authorId1,"Ravan",new Address(45,"Lanka","Matara"));
        Author a2=new Author(authorId2,"Vibhishan",new Address(46,"Lanka","Matara"));

        List<Author> expectedAuthors= List.of(a1);

        when(authorRepo.save(a1)).thenReturn(a1);
        when(authorRepo.save(a2)).thenReturn(a2);

        when(authorRepo.searchAuthorsByNamesLike(authorPattern)).thenReturn(expectedAuthors);

        List<Author> result=authorService.getAllAuthorsByNamesLike(authorPattern);

        assertNotNull(result);
        assertEquals(1,result.size());
        assertEquals(expectedAuthors,result);
        verify(authorRepo,times(1)).searchAuthorsByNamesLike(anyString());
    }
}
