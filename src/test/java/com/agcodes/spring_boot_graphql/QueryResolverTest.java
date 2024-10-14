package com.agcodes.spring_boot_graphql;

import static org.mockito.Mockito.when;

import com.agcodes.spring_boot_graphql.model.Author;
import com.agcodes.spring_boot_graphql.model.Book;
import com.agcodes.spring_boot_graphql.resolver.QueryResolver;
import com.agcodes.spring_boot_graphql.service.AuthorService;
import com.agcodes.spring_boot_graphql.service.BookService;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.graphql.test.tester.GraphQlTester;

@GraphQlTest(QueryResolver.class)
class QueryResolverTest {
  @MockBean
  private BookService bookService;
  @MockBean
  private AuthorService authorService;

  @Autowired
  private GraphQlTester  graphQlTester;


  @Test
    void canGetAllBooks() {
    // Given
    Author author1 = new Author(1, "Test1", 30);
    Author author2 = new Author(2, "Test2", 35);
    List<Book> books = List.of(
        new Book(1, "Book 1", 100, author1),
        new Book(2, "Book 2", 120, author1),
        new Book(3, "Book 3", 140, author2)
    );

    // When
    when(bookService.getAllBooks()).thenReturn(books);

    // When & Then
    graphQlTester
        .documentName("books")
        .execute()
        .path("getAllBooks")
        .entityList(Book.class)
        .hasSize(3);

  }
}