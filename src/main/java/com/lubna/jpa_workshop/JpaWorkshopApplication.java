package com.lubna.jpa_workshop;

import com.lubna.jpa_workshop.dao.BookDao;
import com.lubna.jpa_workshop.dao.BookLoanDao;
import com.lubna.jpa_workshop.dao.UserDao;
import com.lubna.jpa_workshop.entity.Book;
import com.lubna.jpa_workshop.entity.BookLoan;
import com.lubna.jpa_workshop.entity.Details;
import com.lubna.jpa_workshop.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class JpaWorkshopApplication implements CommandLineRunner {

    @Autowired
    UserDao appUserDao;

    @Autowired
    BookDao bookDao;

    @Autowired
    BookLoanDao bookLoanDao;

    public static void main(String[] args) {
        SpringApplication.run(JpaWorkshopApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //creating user 1
        User lubna = new User("Lubna", "12345", LocalDate.now());
        Details details = new Details("lubna@gmail.com", "lubna", LocalDate.now());
        lubna.setDetails(details);
        User userLubna = appUserDao.create(lubna);

        //creating user 2
        User peter = new User("Peter", "John", LocalDate.now());
        Details peterDetails = new Details("peter@gmail.com", "peter", LocalDate.now());
        peter.setDetails(peterDetails);
        appUserDao.create(peter);

        //updating username peter to petty
        peter.setUsername("Petty");
        appUserDao.update(peter);
        System.out.println(appUserDao.findAll());

        //creating book objects
        Book atomicHabits = new Book("23456", "Atomic Habits", 10);
        Book headFirst = new Book("6789", "Head First", 10);

        //persisting books in db
        Book createdBook1 = bookDao.create(atomicHabits);
        Book createdBook2 = bookDao.create(headFirst);


        BookLoan loanAtomic = new BookLoan(LocalDate.now(), LocalDate.parse("2023-02-05"), false);
        bookLoanDao.create(loanAtomic);
        loanAtomic.setBorrower(userLubna);
        loanAtomic.setBook(createdBook1);
        bookLoanDao.update(loanAtomic);

        BookLoan loanHf = new BookLoan(LocalDate.now(), LocalDate.parse("2023-02-05"), false);
        bookLoanDao.create(loanHf);
        loanHf.setBorrower(userLubna);
        loanHf.setBook(createdBook2);
        bookLoanDao.update(loanHf);
    }
}
