package com.bookstore.GeekText.repository;

import com.bookstore.GeekText.model.Books;

import java.util.List;

public interface BookBrowsingRepository {

    List<Books> findOrderedByPriceLimitedTo(int limit);
}
