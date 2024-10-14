package com.agcodes.spring_boot_graphql.repository;

import com.agcodes.spring_boot_graphql.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Long> {

}
