package com.lubna.jpa_workshop.dao.impl;

import com.lubna.jpa_workshop.dao.AppUserDao;
import com.lubna.jpa_workshop.entity.User;
import com.lubna.jpa_workshop.exception.DataNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

@Repository
public class AppUserDaoImpl implements AppUserDao {

    @PersistenceContext
    EntityManager entityManager;


    @Override
    @Transactional(readOnly = true)
    public User findById(int id) {
        User user = entityManager.find(User.class, id);
        return user;
    }

    @Override
    @Transactional
    public void delete(int id) throws DataNotFoundException {
        User user = entityManager.find(User.class, id);
        if (user != null) entityManager.remove(user);
        else throw new DataNotFoundException("AppUser cannot be found");

    }

    @Override
    @Transactional
    public User create(User user) {
        entityManager.persist(user);
        return user;
    }

    @Override
    @Transactional
    public User update(User user) {
        return entityManager.merge(user);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<User> findAll() {
        return entityManager.createQuery("select a from User a", User.class).getResultList();
    }


}
