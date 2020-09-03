package com.bookstore.GeekText.repository;


import com.bookstore.GeekText.model.Books;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class BookBrowsingRepositoryImpl implements BookBrowsingRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Books> findOrderedByPriceLimitedTo(int limit) {
        return entityManager.createQuery("SELECT p FROM Books p ORDER BY p.price", Books.class).setMaxResults(limit).getResultList();
    }

}

