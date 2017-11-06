package org.library.db.domain;

import javax.persistence.*;

@Entity
@Table(name = "author_book")
public class AuthorBook extends Base {
    @ManyToOne
    @JoinColumn(name = "author_id" )
    private Author author;

    @ManyToOne
    @JoinColumn(name = "book_id" )
    private Book book;

    public AuthorBook() {}

    public AuthorBook(Author author, Book book) {
        this.author = author;
        this.book = book;
    }

    public Author getAuthor() {
        return author;
    }
    public void setAuthor(Author author) {
        this.author = author;
    }

    public Book getBook() {
        return book;
    }
    public void setBook(Book book) {
        this.book = book;
    }
}
