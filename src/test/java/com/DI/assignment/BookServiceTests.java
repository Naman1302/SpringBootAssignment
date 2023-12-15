package com.DI.assignment;

import com.DI.assignment.DTO.AuthorDTO;
import com.DI.assignment.DTO.BookDTO;
import com.DI.assignment.Entity.Author;
import com.DI.assignment.Entity.Book;
import com.DI.assignment.Utils.AuthorUtil;
import com.DI.assignment.Utils.BookUtil;
import com.DI.assignment.repository.AuthorRepo;
import com.DI.assignment.repository.BookRepo;
import com.DI.assignment.services.BookService;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceTests {
	@Mock
	private RestTemplate restTemplate;
	@InjectMocks
	private BookService bookService=new BookService(new RestTemplateBuilder());
	@Mock
	private BookRepo bookRepo;
	@Mock
	private AuthorRepo authorRepo;

	private final ObjectId bookId1 = new ObjectId(),bookId2=new ObjectId();
	private final ObjectId authorId1 = new ObjectId(),authorId2=new ObjectId();
	private final List<BookDTO> testBooks= Arrays.asList(new BookDTO(bookId1,"Hellen",57,"Killer",authorId1),new BookDTO(bookId2,"Rola",45,"Thriller",authorId2));

	@Test
	public void getAllBooksTest() {
		when(bookRepo.findAll()).thenReturn(testBooks.stream().map(BookUtil::dtoToEntity).collect(toList()));
		List<BookDTO> expectedBooks=bookService.getAllBooks();
		assertEquals(testBooks.getFirst().getId(),expectedBooks.getFirst().getId());
	}
	@Test
	public void getByGenreTest(){
		String genre="Killer";
		List<BookDTO> expectedBook= List.of(new BookDTO(bookId1,"Hellen", 57, "Killer", authorId1));
		when(bookRepo.searchByGenre(genre)).thenReturn(expectedBook.stream().map(BookUtil::dtoToEntity).toList());
		List<BookDTO> result = bookService.getByGenre(genre);
		assertEquals(testBooks.getFirst().getId(),expectedBook.getFirst().getId());
	}
	@Test
	public void getByGenreAndCopiesCountTest(){
		String genre="Thriller";
		int copies=52;
		List<BookDTO> expectedBook=List.of(new BookDTO(bookId1,"Hellen",57,"Thriller",authorId2));
		when(bookRepo.searchByGenreAndCopiesCount(genre,copies)).thenReturn(expectedBook.stream().map(BookUtil::dtoToEntity).toList());
		List<BookDTO> result=bookService.getByGenreAndCopiesCount(genre,copies);

		assertEquals(result.getFirst().getId(),testBooks.getFirst().getId());
	}
	@Test
	public void getBooksByAuthorsNameTest(){
		String authorsList="Ram:Shyam";

		AuthorDTO a1=new AuthorDTO();
		a1.setName("Ram");
		a1.setId(authorId1);

		AuthorDTO a2=new AuthorDTO();
		a2.setName("Shyam");
		a2.setId(authorId2);

		List<ObjectId> idList= Arrays.asList(authorId1,authorId2);
		List<BookDTO> bookList=Arrays.asList(new BookDTO(bookId1,"Hellen",57,"Romcom",authorId1)
											,new BookDTO(bookId2,"Rola",45,"Thriller",authorId2));

		when(authorRepo.findByName("Ram")).thenReturn(AuthorUtil.dtoToEntity(a1));
		when(authorRepo.findByName("Shyam")).thenReturn(AuthorUtil.dtoToEntity(a2));

		when(bookRepo.findByAuthorIdIn(idList)).thenReturn(bookList.stream().map(BookUtil::dtoToEntity).toList());

		List<BookDTO> expectedBooks=bookService.getBooksByAuthorsName(authorsList);

		assertEquals(2,expectedBooks.size());
		assertEquals(expectedBooks.getFirst().getId(),testBooks.getFirst().getId());
		verify(authorRepo,times(2)).findByName(anyString());
		verify(bookRepo,times(1)).findByAuthorIdIn(idList);
	}
	@Test
	public void getBooksByAuthorsNameByFluxTest(){
		String fluxApi="http://localhost:8081/books/byAuthorsNames",
		       authorList="Ram:Shyam";

		BookDTO[] mockedResponse = {new BookDTO(bookId1,"Hellen",57,"Killer",authorId1),new BookDTO(bookId2,"Rola",45,"Thriller",authorId2)};
		when(restTemplate.getForObject(anyString(), eq(BookDTO[].class))).thenReturn(mockedResponse);

		List<BookDTO> result = bookService.getBooksByAuthorsNameByFlux(authorList);

		System.out.println(result.getFirst().getBookName());
		assertEquals(result,testBooks);
		assertEquals(result.getFirst().getId(),testBooks.getFirst().getId());
		assertEquals(result.getLast().getId(),testBooks.getLast().getId());

		verify(restTemplate, times(1)).getForObject(anyString(), eq(BookDTO[].class), anyString());
	}
	@Test
	public void saveBookTest(){
		BookDTO addBook=testBooks.getFirst();
		String author="Asaram";

		AuthorDTO existingAuthorDTO =new AuthorDTO();
		existingAuthorDTO.setName("Asaram");
		existingAuthorDTO.setId(authorId1);

		when(authorRepo.findByName("Asaram")).thenReturn(AuthorUtil.dtoToEntity(existingAuthorDTO));

		when(bookRepo.save(any(Book.class))).thenReturn(BookUtil.dtoToEntity(addBook));

		BookDTO result=bookService.saveBook(addBook,author);

		assertNotNull(result);
		assertEquals(existingAuthorDTO.getId(),result.getAuthorId());
		assertEquals(bookId1,result.getId());
		verify(authorRepo,times(1)).findByName(anyString());
		verify(bookRepo,times(1)).save(any(Book.class));
	}

}
