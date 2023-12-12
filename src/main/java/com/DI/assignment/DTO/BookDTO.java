package com.DI.assignment.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class BookDTO {
    @Id
    private ObjectId id;
    @NotBlank(message = "Name is mandatory")
    private String bookName;
    @Min(value = 1)
    private int copiesAvailable;
    @NotBlank(message = "Genre cannot be blank")
    private String genre;
    private ObjectId authorId;

    public BookDTO() {
    }

    public BookDTO(ObjectId id,String bookName, int copiesAvailable, String genre, ObjectId authorId) {
        this.id = id;
        this.bookName=bookName;
        this.copiesAvailable = copiesAvailable;
        this.genre = genre;
        this.authorId = authorId;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
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

    public ObjectId getAuthorId() {
        return authorId;
    }

    public void setAuthorId(ObjectId authorId) {
        this.authorId = authorId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
}
