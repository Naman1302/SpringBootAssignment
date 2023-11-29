package com.DI.assignment.repository;

import com.DI.assignment.Entity.Author;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface AuthorRepo extends MongoRepository<Author,String> {
    @Query("{ 'name' : ?0 }")
    Author findByName(String author);
    @Query("{name:{$regex:?0, $options :'i'}}")
    List<Author> searchAuthorsByNamesLike(String authorPattern);
}
