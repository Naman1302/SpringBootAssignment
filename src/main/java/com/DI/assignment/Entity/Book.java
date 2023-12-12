package com.DI.assignment.Entity;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "book")
public class Book {
    @Id
    private ObjectId id;
    @NotBlank(message = "Name is mandatory")
    private String bookName;
    @Min(value = 1)
    private int copiesAvailable;
    @NotBlank(message = "Genre cannot be blank")
    private String genre;
    private ObjectId authorId;

    public Book(ObjectId id,int copiesAvailable,String genre,ObjectId authorId) {
        this.id=id;
        this.copiesAvailable=copiesAvailable;
        this.genre=genre;
        this.authorId=authorId;
    }

    public Book() {

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
    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

}
