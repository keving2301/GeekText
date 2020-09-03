
package com.bookstore.GeekText.repository;

import com.bookstore.GeekText.model.AuthorID;
import com.bookstore.GeekText.model.Authors;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorsRepository extends JpaRepository<Authors, AuthorID> {

}
