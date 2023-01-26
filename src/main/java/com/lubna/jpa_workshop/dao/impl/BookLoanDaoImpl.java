package com.lubna.jpa_workshop.dao.impl;

import com.lubna.jpa_workshop.dao.BookLoanDao;
import com.lubna.jpa_workshop.entity.BookLoan;
import com.lubna.jpa_workshop.exception.DataNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

@Repository
public class BookLoanDaoImpl implements BookLoanDao {

    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    @Override
    public BookLoan create(BookLoan bookLoan) {
        entityManager.persist(bookLoan);
        return bookLoan;
    }
    @Transactional
    @Override
    public BookLoan update(BookLoan bookLoan) {
        return entityManager.merge(bookLoan);
    }

    @Transactional(readOnly = true)
    @Override
    public Collection<BookLoan> findAll() {
        return  entityManager.createQuery("select b from Book b", BookLoan.class)
                .getResultList();
    }

    @Transactional(readOnly = true)
    @Override
    public BookLoan findById(int id) {
        BookLoan bookLoan = entityManager.find(BookLoan.class, id);
        return bookLoan;
    }

    @Transactional
    @Override
    public void delete(int id) throws DataNotFoundException {
        BookLoan bookLoan = entityManager.find(BookLoan.class, id);
        if (bookLoan != null) entityManager.remove(bookLoan);
        else throw new DataNotFoundException("BookLoan cannot be found");

    }
}
