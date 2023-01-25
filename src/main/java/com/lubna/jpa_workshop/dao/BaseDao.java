package com.lubna.jpa_workshop.dao;

import java.util.Collection;

public interface BaseDao<T> {

    T create(T t);

    T update(T t);

    Collection<T> findAll();

}
