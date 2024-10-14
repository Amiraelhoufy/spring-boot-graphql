package com.agcodes.spring_boot_graphql.model;

import static jakarta.persistence.GenerationType.SEQUENCE;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.util.List;

@Entity(name = "Author")
@Table(name = "author")
public class Author {

  @Id
  @SequenceGenerator(
      name = "author_sequence",
      sequenceName = "author_sequence",
      allocationSize = 1
  )
  @GeneratedValue(
      strategy = SEQUENCE,
      generator = "author_sequence"
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
      name="age"
  )
  private int age;


  @OneToMany(
     cascade = CascadeType.ALL,
     mappedBy = "author",
      orphanRemoval = true
  )
  private List<Book> books;

  public Author() {

  }


  public Author(String name, int age) {
    this.name = name;
    this.age = age;
  }

  public Author(long id, String name, int age) {
    this.id = id;
    this.name = name;
    this.age = age;
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

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  @Override
  public String toString() {
    return "Author{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", age=" + age +
        '}';
  }
}
