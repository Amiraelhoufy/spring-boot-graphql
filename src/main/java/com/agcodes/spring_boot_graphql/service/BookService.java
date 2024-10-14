package com.agcodes.spring_boot_graphql.service;

import com.agcodes.spring_boot_graphql.model.Book;
import com.agcodes.spring_boot_graphql.repository.BookRepository;
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
}
