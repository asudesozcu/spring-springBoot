package com.example.jpaandhibernate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name = "Course_Details") // map between JavaBean(class) and table. course details is table name. JPA

public class Course {
    @Column(name = "name") // JPA
    private String name;
    @Column(name = "author") // not mandatory because column name is also author.
    private String author;
    @Id // primary key . JPA
    private long id;

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Course [name=" + name + ", author=" + author + ", id=" + id + "]";
    }

    public Course(String name, String author, int id) {
        super();
        this.name = name;
        this.author = author;
        this.id = id;
    }

    public Course() {

    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
