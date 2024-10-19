package com.agcodes.spring_boot_graphql.resolver;

import com.agcodes.spring_boot_graphql.model.Author;
import com.agcodes.spring_boot_graphql.model.Book;
import com.agcodes.spring_boot_graphql.service.AuthorService;
import com.agcodes.spring_boot_graphql.service.BookService;
import graphql.annotations.annotationTypes.GraphQLField;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

@Controller
public class QueryResolver implements GraphQLQueryResolver {

  private final BookService bookService;
  private final AuthorService authorService;
  @Autowired
  public QueryResolver(BookService bookService,AuthorService authorService) {
    this.bookService = bookService;
    this.authorService = authorService;
  }
  @QueryMapping
  public List<Book> getAllBooks(){
    return bookService.getAllBooks();
  }
  @QueryMapping
  public Integer countBooks(){
    return bookService.getAllBooks().size();
  }

  @QueryMapping
  public Optional<Book> getBookById(@Argument Long id){
    return bookService.getBookById(id);
  }

  @QueryMapping
  public List<Author> getAllAuthors(){
    return authorService.getAllAuthors();
  }

  @QueryMapping
  public Integer countAuthors(){
    return authorService.getAllAuthors().size();
  }
  @QueryMapping
  public Optional<Author> getAuthorById(@Argument Long id){
    return authorService.getAuthorById(id);
  }

  @SchemaMapping // Resolving the 'author' field for the 'Book' type
  public Optional<Author> author(Book book){
    if (book.getAuthor() == null) {
      return Optional.empty(); // Handle Book without an author = empty author
    }
    return authorService.getAuthorById(book.getAuthor().getId());
  }

}
