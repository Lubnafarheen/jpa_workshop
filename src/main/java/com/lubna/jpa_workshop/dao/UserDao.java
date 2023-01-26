package com.lubna.jpa_workshop.dao;

import com.lubna.jpa_workshop.entity.User;
import com.lubna.jpa_workshop.exception.DataNotFoundException;

public interface UserDao extends BaseDao<User> {

    User findById(int id);

    void delete(int id) throws DataNotFoundException;
}
