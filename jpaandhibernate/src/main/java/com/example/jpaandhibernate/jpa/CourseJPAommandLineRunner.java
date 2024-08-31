package com.example.jpaandhibernate.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.jpaandhibernate.Course;

import jakarta.transaction.Transactional;

@Component
@Transactional // when you want to execute query with JPA use this annotation.
public class CourseJPAommandLineRunner implements CommandLineRunner {

    @Autowired
    private CourseJPARepo repository;

    @Override
    public void run(String... args) throws Exception {
        repository.insert(new Course("course1", "author1", 1));
        repository.insert(new Course("course2", "author2", 2));
        repository.insert(new Course("course3", "author3", 3));
        repository.deleteById(1);
        System.out.println(repository.FindbyId(2));
        System.out.println(repository.FindbyId(3));

    }

}
