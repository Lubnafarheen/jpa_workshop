package com.lubna.jpa_workshop.dao;

import com.lubna.jpa_workshop.entity.Details;
import com.lubna.jpa_workshop.exception.DataNotFoundException;

public interface DetailsDao extends BaseDao<Details> {

    Details findById(int id);

    void delete(int id) throws DataNotFoundException;

}
