package com.agcodes.spring_boot_graphql.repository;

import com.agcodes.spring_boot_graphql.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author,Long> {

}
