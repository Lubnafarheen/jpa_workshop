package com.lubna.jpa_workshop.dao.impl;

import com.lubna.jpa_workshop.dao.AuthorDao;
import com.lubna.jpa_workshop.entity.Author;
import com.lubna.jpa_workshop.entity.Book;
import com.lubna.jpa_workshop.exception.DataNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

@Repository
public class AuthorDaoImpl implements AuthorDao {

    @PersistenceContext
    EntityManager entityManager;

    @Transactional(readOnly = true)
    @Override
    public Author findById(int id) {
        Author author = entityManager.find(Author.class, id);
        return author;
    }

    @Transactional
    @Override
    public void delete(int id) throws DataNotFoundException {
        Author author = entityManager.find(Author.class, id);
        if ( author!= null) entityManager.remove(author);
        else throw new DataNotFoundException("Book cannot be found");
    }

    @Transactional
    @Override
    public Author create(Author author) {
        entityManager.persist(author);
        return author;
    }

    @Transactional
    @Override
    public Author update(Author author) {
        return entityManager.merge(author);
    }

    @Transactional(readOnly = true)
    @Override
    public Collection<Author> findAll() {
        return entityManager.createQuery("select a from Author a", Author.class).getResultList();
    }
}
