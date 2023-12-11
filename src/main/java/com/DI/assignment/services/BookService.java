package com.DI.assignment.services;

import com.DI.assignment.DTO.AuthorDTO;
import com.DI.assignment.DTO.BookDTO;
import com.DI.assignment.Entity.Author;
import com.DI.assignment.Entity.Book;
import com.DI.assignment.Utils.AuthorUtil;
import com.DI.assignment.Utils.BookUtil;
import com.DI.assignment.repository.AuthorRepo;
import com.DI.assignment.repository.BookRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
    @Autowired
    private BookRepo bookRepo;
    @Autowired
    private AuthorRepo authorRepo;
    public BookDTO saveBook(BookDTO bookDTO, String author){
            AuthorDTO foundAuthorDTO= AuthorUtil.entityToDTO(authorRepo.findByName(author));
            if(foundAuthorDTO==null){
                throw new IllegalArgumentException("No Author found");
            }
            else{
                bookDTO.setAuthorId(foundAuthorDTO.getId());
            }
            BookDTO savedBook=BookUtil.entityToDTO(bookRepo.save(BookUtil.dtoToEntity(bookDTO)));
            foundAuthorDTO.getBookList().add(savedBook.getId());
            authorRepo.save(AuthorUtil.dtoToEntity(foundAuthorDTO));
            return savedBook;
    }
    public List<BookDTO> getAllBooks(){
        List<Book> books=bookRepo.findAll();

        return books.stream().map(BookUtil::entityToDTO).collect(Collectors.toList());
    }
    public List<BookDTO> getByGenre(String genre){
        List<Book> books=bookRepo.searchByGenre(genre);

        return books.stream().map(BookUtil::entityToDTO).collect(Collectors.toList());
    }
    public List<BookDTO> getByGenreAndCopiesCount(String genre,int copies){
        List<Book> books= bookRepo.searchByGenreAndCopiesCount(genre,copies);

        return books.stream().map(BookUtil::entityToDTO).collect(Collectors.toList());
    }

    public List<BookDTO> getBooksByAuthorsName(String authorList) {
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
        return fetchedBooks.stream().map(BookUtil::entityToDTO).collect(Collectors.toList());
    }
}
