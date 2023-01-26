package com.lubna.jpa_workshop.dao;

import com.lubna.jpa_workshop.entity.Book;
import com.lubna.jpa_workshop.exception.DataNotFoundException;

public interface BookDao extends BaseDao<Book>{

   Book findById(int id);

    void delete(int id) throws DataNotFoundException;
}
