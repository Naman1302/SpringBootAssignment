package com.DI.assignment.Entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "author")
public class Author {
    @Id
    public ObjectId id;
    private String name;
    private Address address;
    private List<ObjectId> bookList;

    public Author() {
        this.bookList=new ArrayList<>();
    }

    public Author(ObjectId id, String name, Address address) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.bookList=new ArrayList<>();
    }

    public List<ObjectId> getBookList() {
        return bookList;
    }

    public void setBookList(List<ObjectId> bookList) {
        this.bookList = bookList;
    }

    public ObjectId getid() {
        return id;
    }

    public void setid(ObjectId id) {
        this.id = id;
    }

    public Author(String name, Address address) {
        this.name = name;
        this.address = address;
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


}
