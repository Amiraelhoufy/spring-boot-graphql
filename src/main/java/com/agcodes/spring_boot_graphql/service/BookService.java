package com.agcodes.spring_boot_graphql.service;

import com.agcodes.spring_boot_graphql.model.Author;
import com.agcodes.spring_boot_graphql.model.Book;
import com.agcodes.spring_boot_graphql.repository.BookRepository;
import graphql.GraphQLException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {


  private final BookRepository bookRepository;
  @Autowired
  public BookService(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  public List<Book> getAllBooks(){
    return bookRepository.findAll();
//    return books != null ? books : Collections.emptyList(); // Avoid returning null

  }

  public Optional<Book> getBookById(Long id){
    return bookRepository.findById(id);
  }

  public Book createBook(String name, Integer pageCount, Author author) {

    if(bookRepository.existsByName(name)){
      throw new GraphQLException("This book name already exists!");
    }

    Book book = new Book();
    book.setName(name);
    book.setPageCount(pageCount);
    book.setAuthor(author);

    return bookRepository.save(book);

  }


  public Book updateBook(long id, String name, Integer pageCount, Author author) {

    Optional<Book> bookOptional = bookRepository.findById(id);
    if(bookOptional.isPresent()){
      Book book = bookOptional.get();

      if(name!= null){

        if (bookRepository.existsByName(name)) {
          throw new GraphQLException("Book with this name already exists!");

        }

        book.setName(name);
      }
      if(pageCount!=null){
        book.setPageCount(pageCount);
      }

      // Update the author reference
      book.setAuthor(author);

      // Save and return the updated book
      return bookRepository.save(book);

    }else{
      throw new GraphQLException("Book with id "+ id + " not found!");
    }

  }

  public boolean deleteBook(long id) {
    if(!bookRepository.existsById(id)){
      return false;
    }
    bookRepository.deleteById(id);
    return true;
  }

}
