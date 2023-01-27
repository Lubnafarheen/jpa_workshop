package com.lubna.jpa_workshop.entity;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int authorId;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false, length = 300)
    private String lastName;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH})
    private Set<Book> writtenBooks;

    public Author() {
    }

    public Author(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Author(String firstName, String lastName, Set<Book> writtenBooks) {
        this();
        this.writtenBooks = writtenBooks;
    }

    public void addBook(Book book) {
        if (book == null) throw new IllegalArgumentException("book was null");
        if (writtenBooks == null) writtenBooks = new HashSet<>();
        writtenBooks.add(book);
        book.getAuthorList().add(this);
    }

    public void removeBook(Book book) {
        if (book == null) throw new IllegalArgumentException("book was null");
        if (writtenBooks != null) {
            book.getAuthorList().remove(this);
            writtenBooks.remove(book);
        }
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<Book> getWrittenBooks() {
        return writtenBooks;
    }

    public void setWrittenBooks(Set<Book> writtenBooks) {
        this.writtenBooks = writtenBooks;
    }
}
