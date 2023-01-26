package com.lubna.jpa_workshop.dao.impl;

import com.lubna.jpa_workshop.dao.BookDao;
import com.lubna.jpa_workshop.entity.Book;
import com.lubna.jpa_workshop.exception.DataNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

@Repository
public class BookDaOImpl implements BookDao {

    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    @Override
    public Book create(Book book) {
        entityManager.persist(book);
        return book;
    }

    @Transactional
    @Override
    public Book update(Book book) {
        return entityManager.merge(book);
    }

    @Transactional(readOnly = true)
    @Override
    public Collection<Book> findAll() {
        return entityManager.createQuery("select b from Book b", Book.class).getResultList();
    }

    @Transactional(readOnly = true)
    @Override
    public Book findById(int id) {
        Book book = entityManager.find(Book.class, id);
        return book;
    }

    @Transactional
    @Override
    public void delete(int id) throws DataNotFoundException {
        Book book = entityManager.find(Book.class, id);
        if (book != null) entityManager.remove(book);
        else throw new DataNotFoundException("Book cannot be found");
    }
}
