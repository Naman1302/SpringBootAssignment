package com.DI.assignment.DTO;

import com.DI.assignment.Entity.Book;

public class BookInsertDTO {
    private BookDTO book;
    private String authorName;

    public BookDTO getBook() {
        return book;
    }

    public void setBook(BookDTO book) {
        this.book = book;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
}
