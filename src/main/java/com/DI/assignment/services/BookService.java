package com.DI.assignment.services;

import com.DI.assignment.Entity.Author;
import com.DI.assignment.Entity.Book;
import com.DI.assignment.repository.AuthorRepo;
import com.DI.assignment.repository.BookRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepo bookRepo;
    @Autowired
    private AuthorRepo authorRepo;
    public Book saveBook(Book book, String author){
        if( book!=null && book.getCopiesAvailable()>=0 && !author.isEmpty() && book.getGenre()!=null ) {
            Author foundAuthor=authorRepo.findByName(author);
            if(foundAuthor==null){
                Author newAuthor=new Author();
                newAuthor.setName(author);
                authorRepo.save(newAuthor);
                book.setAuthorId(newAuthor.getid());
            }
            else{
                book.setAuthorId(foundAuthor.getid());
            }
            Book savedBook=bookRepo.save(book);
            foundAuthor.getBookList().add(savedBook.getId());
            authorRepo.save(foundAuthor);
            return savedBook;
        }
        else{
            throw new IllegalArgumentException("Invalid/Incomplete details given");
        }
    }
    public List<Book> getAllBooks(){
        return bookRepo.findAll();
    }
    public List<Book> getByGenre(String genre){
        return bookRepo.searchByGenre(genre);
    }
    public List<Book> getByGenreAndCopiesCount(String genre,int copies){
        return bookRepo.searchByGenreAndCopiesCount(genre,copies);
    }

    public List<Book> getBooksByAuthorsName(String authorList) {
        String[] authors= authorList.split(":");
        List<Book> fetchedBooks=new ArrayList<>();
        for (String author : authors) {
            Author test = authorRepo.findByName(author);
            if(test!=null){
                List<ObjectId> list=test.getBookList();
                list.forEach(
                        (bookId)-> {
                            Book fetchedBook=bookRepo.getById(bookId);
                            fetchedBooks.add(fetchedBook);
                        }
                );
            }
        }
        return fetchedBooks;
    }
}
