package com.DI.assignment.DTO;

import com.DI.assignment.Entity.Book;

public class BookInsertDTO {
    private Book book;
    private String authorName;

    public Book getBook() {
        return book;
    }

    @Override
    public String toString() {
        return "BookInsertDTO{" +
                "book=" + book.getCopiesAvailable() + " " +book.getGenre()+
                ", authorName='" + authorName + '\'' +
                '}';
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
}
