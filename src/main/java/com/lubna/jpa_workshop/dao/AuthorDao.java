package com.lubna.jpa_workshop.dao;

import com.lubna.jpa_workshop.entity.Author;
import com.lubna.jpa_workshop.exception.DataNotFoundException;

public interface AuthorDao extends BaseDao<Author> {

    Author findById(int id);

    void delete(int id) throws DataNotFoundException;
}
