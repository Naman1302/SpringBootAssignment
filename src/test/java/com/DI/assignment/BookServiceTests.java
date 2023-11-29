package com.DI.assignment;

import com.DI.assignment.Entity.Author;
import com.DI.assignment.Entity.Book;
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
	private final List<Book> testBooks= Arrays.asList(new Book(bookId1,57,"Killer",authorId1),new Book(bookId2,45,"Thriller",authorId2));

	@Test
	public void getAllBooksTest() {
		when(bookRepo.findAll()).thenReturn(testBooks);
		List<Book> expectedBooks=bookService.getAllBooks();
		assertEquals(testBooks,expectedBooks);
	}
	@Test
	public void getByGenreTest(){
		String genre="Killer";
		List<Book> expectedBook= List.of(new Book(bookId1, 57, "Killer", authorId1));
		when(bookRepo.searchByGenre(genre)).thenReturn(expectedBook);
		List<Book> result = bookService.getByGenre(genre);
		assertEquals(result,expectedBook);
	}
	@Test
	public void getByGenreAndCopiesCountTest(){
		String genre="Thriller";
		int copies=52;
		List<Book> expectedBook=List.of(new Book(bookId2,57,"Thriller",authorId2));
		when(bookRepo.searchByGenreAndCopiesCount(genre,copies)).thenReturn(expectedBook);
		List<Book> result=bookService.getByGenreAndCopiesCount(genre,copies);
		assertEquals(result,expectedBook);
	}
	@Test
	public void getBooksByAuthorsNameTest(){
		String authorsList="Ram:Shyam";

		Author a1=new Author();
		a1.setName("Ram");
		a1.getBookList().add(bookId1);

		Author a2=new Author();
		a2.setName("Shyam");
		a2.getBookList().add(bookId2);

		when(authorRepo.findByName("Ram")).thenReturn(a1);
		when(authorRepo.findByName("Shyam")).thenReturn(a2);

		when(bookRepo.getById(bookId1)).thenReturn(new Book(bookId1,45,"Romcom",authorId1));
		when(bookRepo.getById(bookId2)).thenReturn(new Book(bookId2,43,"Thriller",authorId2));

		List<Book> expectedBooks=bookService.getBooksByAuthorsName(authorsList);

		assertEquals(2,expectedBooks.size());
		verify(authorRepo,times(2)).findByName(anyString());
		verify(bookRepo,times(1)).getById(bookId1);
		verify(bookRepo,times(1)).getById(bookId2);

	}
	@Test
	public void saveBookTest(){
		Book addBook=testBooks.getFirst();
		String author="Asaram";

		Author existingAuthor =new Author();
		existingAuthor.setName("Asaram");
		existingAuthor.setid(authorId1);

		when(authorRepo.findByName("Asaram")).thenReturn(existingAuthor);

		when(bookRepo.save(any(Book.class))).thenAnswer(invocation -> {
			Book savedBook = invocation.getArgument(0);
			savedBook.setId(bookId1);
			return savedBook;
		});

		Book result=bookService.saveBook(addBook,author);

		assertNotNull(result);
		assertEquals(existingAuthor.getid(),result.getAuthorId());
		assertEquals(bookId1,result.getId());
		verify(authorRepo,times(1)).findByName(anyString());
		verify(bookRepo,times(1)).save(addBook);
	}

}
