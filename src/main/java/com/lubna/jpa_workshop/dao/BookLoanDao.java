package com.lubna.jpa_workshop.dao;

import com.lubna.jpa_workshop.entity.BookLoan;
import com.lubna.jpa_workshop.exception.DataNotFoundException;

public interface BookLoanDao extends BaseDao<BookLoan>{

    BookLoan findById(int id);

    void delete(int id) throws DataNotFoundException;
}
