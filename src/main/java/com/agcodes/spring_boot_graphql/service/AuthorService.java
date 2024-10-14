package com.agcodes.spring_boot_graphql.service;

import com.agcodes.spring_boot_graphql.model.Author;
import com.agcodes.spring_boot_graphql.model.Book;
import com.agcodes.spring_boot_graphql.repository.AuthorRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

  private AuthorRepository authorRepository;

  @Autowired
  public AuthorService(AuthorRepository authorRepository) {
    this.authorRepository = authorRepository;
  }

  public List<Author> getAllAuthors(){
    return authorRepository.findAll();
  }

  // @Transactional to ensure the session is active using lazy loading for the Author
  @Transactional
  public Optional<Author> getAuthorById(Long id){
    return authorRepository.findById(id);
  }

}
