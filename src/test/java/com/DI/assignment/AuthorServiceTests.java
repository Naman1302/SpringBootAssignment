package com.DI.assignment;

import com.DI.assignment.DTO.AuthorDTO;
import com.DI.assignment.DTO.Address;
import com.DI.assignment.Entity.Author;
import com.DI.assignment.Utils.AuthorUtil;
import com.DI.assignment.repository.AuthorRepo;
import com.DI.assignment.services.AuthorService;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.Collectors;


import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class AuthorServiceTests {
    @InjectMocks
    private AuthorService authorService;
    @Mock(lenient = true)
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

        when(authorRepo.findAll()).thenReturn(authorList
                        .stream()
                        .map(AuthorUtil::dtoToEntity)
                        .collect(Collectors.toList())
        );

        List<AuthorDTO> result=authorService.getAllAuthors();

        assertNotNull(result);
        assertEquals(2,result.size());
        assertEquals(authorList.getFirst().getId(),result.getFirst().getId());
        assertEquals(authorList.getLast().getId(),result.getLast().getId());
        verify(authorRepo,times(1)).findAll();
    }
    @Test
    public void addAuthorTest() {
        AuthorDTO testAuthor = new AuthorDTO();
        testAuthor.setId(authorId1);
        testAuthor.setName("Ram");
        testAuthor.setAddress(new Address(56, "Lalu", "Mongol"));

        when(authorRepo.save(any(Author.class))).thenReturn(AuthorUtil.dtoToEntity(testAuthor));
        AuthorDTO result = authorService.addAuthor(testAuthor);

        verify(authorRepo, times(1)).save(any(Author.class));
        assertNotNull(result);
        assertEquals(testAuthor.getId(), result.getId());
    }
    @Test
    public void getAllAuthorsByNamesLikeTest(){
        String authorPattern="Ra";

        AuthorDTO a1=new AuthorDTO(authorId1,"Ravan",new Address(45,"Lanka","Matara"));
        AuthorDTO a2=new AuthorDTO(authorId2,"Vibhishan",new Address(46,"Lanka","Matara"));

        List<AuthorDTO> expectedAuthors= List.of(a1);

        when(authorRepo.save(AuthorUtil.dtoToEntity(a1))).thenReturn(AuthorUtil.dtoToEntity(a1));
        when(authorRepo.save(AuthorUtil.dtoToEntity(a2))).thenReturn(AuthorUtil.dtoToEntity(a2));

        when(authorRepo.searchAuthorsByNamesLike(authorPattern)).thenReturn(expectedAuthors.stream().map(AuthorUtil::dtoToEntity).collect(Collectors.toList()));

        List<AuthorDTO> result=authorService.getAllAuthorsByNamesLike(authorPattern);

        assertNotNull(result);
        assertEquals(1,result.size());
        assertEquals(expectedAuthors.getFirst().getId(),result.getFirst().getId());
        verify(authorRepo,times(1)).searchAuthorsByNamesLike(anyString());
    }

}
