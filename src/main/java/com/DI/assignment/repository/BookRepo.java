package com.DI.assignment.repository;

import com.DI.assignment.Entity.Book;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface BookRepo extends MongoRepository<Book,String> {
    @Query("{genre : ?0}")
    List<Book> searchByGenre(String genre);
    @Query("{'genre' : ?0, 'copiesAvailable' : {$gte : ?1} }")
    List<Book> searchByGenreAndCopiesCount(String genre, int copiesAvailable);

    @Query("{_id : ?0}")
    Book getById(ObjectId bookId);
}
