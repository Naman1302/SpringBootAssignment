package com.DI.assignment.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

@Getter
public class BookDTO {

    private ObjectId id;

    @NotBlank(message = "Name is mandatory")
    private String bookName;

    @Min(value = 0)
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

    public void setId(ObjectId id) {
        this.id = id;
    }

    public void setCopiesAvailable(int copiesAvailable) {
        this.copiesAvailable = copiesAvailable;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setAuthorId(ObjectId authorId) {
        this.authorId = authorId;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
}
