package com.agcodes.spring_boot_graphql.service;

import com.agcodes.spring_boot_graphql.model.Author;
import com.agcodes.spring_boot_graphql.repository.AuthorRepository;
import graphql.GraphQLException;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
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

  public Author createAuthor(String name, int age) {

    if (authorRepository.existsByName(name)) {
      throw new GraphQLException("Author with this name already exists!");

    }
    Author author = new Author(name,age);
    return authorRepository.save(author);
  }

  public Author updateAuthor(Long id,String name,Integer age){

    /*
    // You can update all the fields even it's changed or not
    if(!authorRepository.existsById(id)){
          throw new GraphQLException("Author with this name already exists!");
    }
    Author author= new Author(id,name,age);
    authorRepository.save(author);
    return author;
    */

    // Better approach just update the provided fields

      Author author = findById(id);

      if(name!=null){
        if (authorRepository.existsByName(name)) {
          throw new GraphQLException("Author with this name already exists!");

        }
        author.setName(name);
      }

      if(age!=null){
        author.setAge(age);
      }

      return authorRepository.save(author);

  }

  public Boolean deleteAuthor(Long id) {

    if(!authorRepository.existsById(id)){
      return false;
    }

    authorRepository.deleteById(id);
    return true;
  }


  public Author findById(long authorId) {

    Optional<Author> authorOptional = authorRepository.findById(authorId);
    if(authorOptional.isPresent()){
      return authorOptional.get();
    }else{
      throw new GraphQLException("Author with ID " + authorId + " not found");
    }
  }
}
