package com.DI.assignment.Entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "book")
public class Book {
    @Id
    private ObjectId id;

    private int copiesAvailable;
    private String genre;
    private ObjectId authorId;

    public Book(ObjectId id,int copiesAvailable,String genre,ObjectId authorId) {
        this.id=id;
        this.copiesAvailable=copiesAvailable;
        this.genre=genre;
        this.authorId=authorId;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public ObjectId getAuthorId() {
        return authorId;
    }


    public void setAuthorId(ObjectId authorId) {
        this.authorId = authorId;
    }

    public int getCopiesAvailable() {
        return copiesAvailable;
    }

    public void setCopiesAvailable(int copiesAvailable) {
        this.copiesAvailable = copiesAvailable;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

}
