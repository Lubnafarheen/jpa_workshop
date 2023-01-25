package com.lubna.jpa_workshop.dao.impl;

import com.lubna.jpa_workshop.dao.DetailsDao;
import com.lubna.jpa_workshop.entity.Details;
import com.lubna.jpa_workshop.exception.DataNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

@Repository
public class DetailsDaoImpl implements DetailsDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public Details create(Details details) {
        entityManager.persist(details);
        return details;
    }

    @Override
    @Transactional
    public Details update(Details details) {
        return entityManager.merge(details);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Details> findAll() {
        return entityManager.createQuery("select d from Details d", Details.class).getResultList();
    }


    @Override
    @Transactional(readOnly = true)
    public Details findById(int id) {
        Details result = entityManager.find(Details.class, id);
        return result;
    }

    @Override
    @Transactional
    public void delete(int id) throws DataNotFoundException {
        Details result = entityManager.find(Details.class, id);
        if (result != null) entityManager.remove(result);
        else throw new DataNotFoundException("Details cannot be found");
    }
}
