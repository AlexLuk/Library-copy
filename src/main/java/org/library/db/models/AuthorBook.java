package org.library.db.models;

import javax.persistence.*;

@Entity
@Table(name = "author_book")

@NamedQueries
({
    @NamedQuery( name = AuthorBook.GET_AUTHOR_ID,
            query = "SELECT a from AuthorBook a where a.author = :authorID" )
})

public class AuthorBook extends Base {
    public static final String GET_AUTHOR_ID = "Author.getAuthorId";

    @ManyToOne
    @JoinColumn(name = "author_id" )
    private Author author;

    @ManyToOne
    @JoinColumn(name = "book_id" )
    private Book book;

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
