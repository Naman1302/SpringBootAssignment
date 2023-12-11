package com.DI.assignment.DTO;

import com.DI.assignment.Entity.Address;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

public class AuthorDTO {
    @Id
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

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<ObjectId> getBookList() {
        return bookList;
    }

    public void setBookList(List<ObjectId> bookList) {
        this.bookList = bookList;
    }
}
