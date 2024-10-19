package com.agcodes.spring_boot_graphql.resolver;

import com.agcodes.spring_boot_graphql.model.Author;
import com.agcodes.spring_boot_graphql.model.Book;
import com.agcodes.spring_boot_graphql.service.AuthorService;
import com.agcodes.spring_boot_graphql.service.BookService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
public class MutationResolver implements GraphQLMutationResolver {

  private final BookService bookService;
  private final AuthorService authorService;

  @Autowired
  public MutationResolver(BookService bookService, AuthorService authorService) {
    this.bookService = bookService;
    this.authorService = authorService;
  }

  @MutationMapping
  public Author createAuthor(@Argument String name,@Argument int age){
    Author author = authorService.createAuthor(name, age);
    return author;
  }

  @MutationMapping
  public Author updateAuthor(@Argument Long id,@Argument String name,@Argument Integer age){
      return authorService.updateAuthor(id, name, age);

  }

  @MutationMapping
  public boolean deleteAuthor(@Argument Long id){
    return authorService.deleteAuthor(id);
  }


  @MutationMapping
  public Book createBook(@Argument String name,@Argument Integer pageCount,@Argument long authorId){

    // Check if the author exists
    Author author = authorService.findById(authorId);

    return bookService.createBook(name,pageCount,author);
  }
@MutationMapping
  public Book updateBook(@Argument long id, @Argument String name,@Argument Integer pageCount,@Argument long authorId){

  // Check if the author exists
  Author author = authorService.findById(authorId);

  return bookService.updateBook(id, name, pageCount, author);
  }
  @MutationMapping
  public boolean deleteBook(@Argument long id){
    return bookService.deleteBook(id);
  }

}
