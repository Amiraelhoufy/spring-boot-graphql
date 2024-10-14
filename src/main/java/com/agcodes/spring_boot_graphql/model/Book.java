package com.agcodes.spring_boot_graphql.model;

import static jakarta.persistence.GenerationType.SEQUENCE;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.util.List;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibilities.Private;

@Entity(name = "Book")
@Table(name ="book")
public class Book{
  @Id
  @SequenceGenerator(
      name = "book_sequence",
      sequenceName = "book_sequence",
      allocationSize = 1
  )
  @GeneratedValue(
      strategy = SEQUENCE,
      generator = "book_sequence"
  )
  @Column(
      name = "id",
      updatable = false
  )
  private long id;
  @Column(
      name="name",
      nullable = false
  )
  private String name;


  @Column(
      name="page_count",
      nullable = false
  )
  private int pageCount;

  @ManyToOne
  @JoinColumn(
      name = "author_id", // column name in this table
      referencedColumnName = "id", // column in Author
      foreignKey = @ForeignKey(
          name = "author_id_fk" // Renaming foreign key constraint
      )
  )
  private Author author;

  public Book() {

  }

  public Book(long id, String name, int pageCount) {
    this.id = id;
    this.name = name;
    this.pageCount = pageCount;
  }

  public Book(String name, int pageCount) {
    this.name = name;
    this.pageCount = pageCount;
  }

  public Book(long id, String name, int pageCount, Author author) {
    this.id = id;
    this.name = name;
    this.pageCount = pageCount;
    this.author = author;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getPageCount() {
    return pageCount;
  }

  public void setPageCount(int pageCount) {
    this.pageCount = pageCount;
  }


  public Author getAuthor() {
    return author;
  }

  @Override
  public String toString() {
    return "Book{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", pageCount=" + pageCount +
        '}';
  }

}
