package com.lubna.jpa_workshop.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 100, unique = true)
    private String username;

    @Column(nullable = false, length = 100)
    private String password;

    private LocalDate registrationDate;

    @OneToOne(cascade = { CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH, CascadeType.REMOVE} )
    @JoinColumn(name = "details_id")
    private Details details;

    @OneToMany(mappedBy = "borrower",  orphanRemoval = true)
    private List<BookLoan> loans;

    public User() {
    }

    public User(String username, String password, LocalDate registrationDate) {
        this.username = username;
        this.password = password;
        this.registrationDate = registrationDate;
    }

    public User(String username, String password, LocalDate registrationDate, Details details) {
        this();
        this.details = details;
    }

    @Override
    public String toString() {
        return "AppUser{" + "appUserId=" + id + ", username='" + username + '\'' + ", password='" + password + '\'' + ", registrationDate=" + registrationDate + ", details=" + details + '}';
    }

    public List<BookLoan> getLoans() {
        return loans;
    }

    public void setLoans(List<BookLoan> loans) {
        this.loans = loans;
    }

    public int getId() {
        return id;
    }

    public void setId(int appUserId) {
        this.id = appUserId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Details getDetails() {
        return details;
    }

    public void setDetails(Details details) {
        this.details = details;
    }

    public void addBookLoan(BookLoan bookLoan){
        if (bookLoan == null) throw new IllegalArgumentException("BookLoan was null");
        if (loans == null) loans = new ArrayList<>();
        loans.add(bookLoan);
        bookLoan.setBorrower(this);
    }

    public void removeBookLoan(BookLoan bookLoan){
        if (bookLoan == null) throw new IllegalArgumentException("BookLoan was null");
        if (loans !=null){
            bookLoan.setBorrower(null);
            loans.remove(bookLoan);
        }
    }
}
