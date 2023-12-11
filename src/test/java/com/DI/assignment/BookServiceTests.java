package com.DI.assignment;

import com.DI.assignment.DTO.AuthorDTO;
import com.DI.assignment.DTO.BookDTO;
import com.DI.assignment.Entity.Author;
import com.DI.assignment.Entity.Book;
import com.DI.assignment.Utils.BookUtil;
import com.DI.assignment.repository.AuthorRepo;
import com.DI.assignment.repository.BookRepo;
import com.DI.assignment.services.BookService;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class BookServiceTests {
	@InjectMocks
	private BookService bookService;
	@Mock
	private BookRepo bookRepo;
	@Mock
	private AuthorRepo authorRepo;

	private final ObjectId bookId1 = new ObjectId(),bookId2=new ObjectId();
	private final ObjectId authorId1 = new ObjectId(),authorId2=new ObjectId();
	private final List<BookDTO> testBooks= Arrays.asList(new BookDTO(bookId1,57,"Killer",authorId1),new BookDTO(bookId2,45,"Thriller",authorId2));

	@Test
	public void getAllBooksTest() {
		when(bookRepo.findAllAsDTO()).thenReturn(testBooks);
		List<BookDTO> expectedBooks=bookService.getAllBooks();
		assertEquals(testBooks,expectedBooks);
	}
	@Test
	public void getByGenreTest(){
		String genre="Killer";
		List<BookDTO> expectedBook= List.of(new BookDTO(bookId1, 57, "Killer", authorId1));
		when(bookRepo.searchByGenre(genre)).thenReturn(expectedBook);
		List<BookDTO> result = bookService.getByGenre(genre);
		assertEquals(result,expectedBook);
	}
	@Test
	public void getByGenreAndCopiesCountTest(){
		String genre="Thriller";
		int copies=52;
		List<BookDTO> expectedBook=List.of(new BookDTO(bookId2,57,"Thriller",authorId2));
		when(bookRepo.searchByGenreAndCopiesCount(genre,copies)).thenReturn(expectedBook);
		List<BookDTO> result=bookService.getByGenreAndCopiesCount(genre,copies);
		assertEquals(result,expectedBook);
	}
	@Test
	public void getBooksByAuthorsNameTest(){
		String authorsList="Ram:Shyam";

		AuthorDTO a1=new AuthorDTO();
		a1.setName("Ram");
		a1.getBookList().add(bookId1);

		AuthorDTO a2=new AuthorDTO();
		a2.setName("Shyam");
		a2.getBookList().add(bookId2);

		when(authorRepo.findByName("Ram")).thenReturn(a1);
		when(authorRepo.findByName("Shyam")).thenReturn(a2);

		when(bookRepo.getById(bookId1)).thenReturn(new Book(bookId1,45,"Romcom",authorId1));
		when(bookRepo.getById(bookId2)).thenReturn(new Book(bookId2,43,"Thriller",authorId2));

		List<BookDTO> expectedBooks=bookService.getBooksByAuthorsName(authorsList);

		assertEquals(2,expectedBooks.size());
		verify(authorRepo,times(2)).findByName(anyString());
		verify(bookRepo,times(1)).getById(bookId1);
		verify(bookRepo,times(1)).getById(bookId2);

	}
	@Test
	public void saveBookTest(){
		BookDTO addBook=testBooks.getFirst();
		String author="Asaram";

		AuthorDTO existingAuthorDTO =new AuthorDTO();
		existingAuthorDTO.setName("Asaram");
		existingAuthorDTO.setId(authorId1);

		when(authorRepo.findByName("Asaram")).thenReturn(existingAuthorDTO);

		when(bookRepo.save(BookUtil.dtoToEntity(addBook))).thenReturn(addBook);

		BookDTO result=bookService.saveBook(addBook,author);

		assertNotNull(result);
		assertEquals(existingAuthorDTO.getId(),result.getAuthorId());
		assertEquals(bookId1,result.getId());
		verify(authorRepo,times(1)).findByName(anyString());
		verify(bookRepo,times(1)).save(BookUtil.dtoToEntity(addBook));
	}

}
