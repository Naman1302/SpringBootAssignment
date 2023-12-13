package com.DI.assignment.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

@Getter
public class AuthorDTO {

    public ObjectId id;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotNull(message = "Address is mandatory")
    private Address address;

    private List<ObjectId> bookList;

    public AuthorDTO() {
        this.bookList=new ArrayList<>();
    }

    public AuthorDTO(ObjectId id, String name, Address address) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.bookList=new ArrayList<>();
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setBookList(List<ObjectId> bookList) {
        this.bookList = bookList;
    }
}
