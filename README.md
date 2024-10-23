# Spring Boot GraphQL Application

## Overview
This project demonstrates building a flexible and efficient API with GraphQL which is a data and query manipulation language for APIs,
allowing clients to request only the data they need in a single request to a single endpoint.


## What is GraphQL?
GraphQL is a data query and manipulation language designed for APIs.
 It provides a more efficient and flexible approach than REST by enabling clients to request specific fields and data in a single query.

## Why GraphQL?
- **Efficient:** Clients can request specific fields/data.
- **Strongly Typed:** Ensures type safety.
- **No API Versioning:** Flexibility in schema evolution without breaking existing queries.

## GraphQL UI
Access the GraphiQL interface via: **http://localhost:8080/graphiql?path=/graphql**

## GraphQL Operations:
1. **Queries**: Fetch data.
2. **Mutations**: Modify data.
3. **Subscriptions**: Subscribe to real-time updates.

## GraphQL HTTP API
- **GET request**: Query in request parameters  &rarr; not useful if you have long queries.
Or
- **POST request**: Query in request body.

## GraphQL Annotations
Spring Boot provides several GraphQL-specific annotations to make development easier:

- **@QueryMapping**: Maps GraphQL queries to methods in the Spring Boot application.
- **@MutationMapping**: Maps GraphQL mutations.
- **@SchemaMapping**: Maps schema relationships.
- **@SubscriptionMapping**: Maps subscription resolvers.

## Project Structure
- Model Classes: Define the entities for GraphQL, such as Book and Author.
- Repositories: Use Spring Data JPA to interact with the database.
- Controllers: Define GraphQL resolvers for queries and mutations.

## Example query:
```graphql
{
  books {
    title
    author {
      name
    }
  }
}
```

## Example mutation:

```graphql
mutation {
  addBook(title: "New Book", authorId: 1) {
    id
    title
  }
}
```

## Example Queries
- Fetch All Books:
```graphql
{
  books {
    id
    title
    author {
      name
    }
  }
}
```

- Add a New Book:
```graphql
mutation {
  addBook(title: "GraphQL in Action", authorId: 1) {
    id
    title
  }
}
```

- Update a Book:

```graphql
mutation {
  updateBook(id: 1, title: "Updated Book Title") {
    id
    title
  }
}
```

## Installation
1. **Clone the repository:**
```bash
git clone https://github.com/Amiraelhoufy/spring-boot-graphql.git
cd spring-boot-graphql
```

2. **Configure your database in application.properties::**
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/your_database
spring.datasource.username=your_username
spring.datasource.password=your_password
```

3. **Build and run the application:**
```bash
mvn clean install
mvn spring-boot:run
```

4. **Access the GraphQL UI (GraphiQL) at:**
Once the application is running, you can interact with the API via GraphiQL at **http://localhost:8080/graphiql**
This interface allows you to test queries, mutations, and view the schema.


## References
- **Amigoscode Explanation**: Watch the detailed tutorial on YouTube here [Spring Boot and GraphQL Tutorial](https://www.youtube.com/watch?v=uNB2N_w_ypo)
- **Technical Implementation Guide**: Explore similar implementation steps [Spring Boot + GraphQL + MySQL](https://www.bezkoder.com/spring-boot-graphql-mysql-jpa/#google_vignette)
- **Further Reading**: Learn about GraphQL operations in more detail on the [Daily.dev blog](https://daily.dev/blog/graphql-field-types-explained#what-are-the-three-types-of-operations-in-graphql)
