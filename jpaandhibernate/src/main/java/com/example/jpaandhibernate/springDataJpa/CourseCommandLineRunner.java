package com.example.jpaandhibernate.springDataJpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.jpaandhibernate.Course;

@Component
public class CourseCommandLineRunner implements CommandLineRunner {

    @Autowired
    CourseSpringDataJpaRepo repository;

    @Override
    public void run(String... args) throws Exception {
        repository.save(new Course("course1", "author1", 1));
        repository.save(new Course("course2", "author2", 2));
        repository.save(new Course("course3", "author3", 3));
        repository.deleteById(1l);
        System.out.println(repository.findById(2l));
        System.out.println(repository.findById(3l));

        System.out.println(repository.findAll());
        System.out.println(repository.count());
        System.out.println(repository.findByAuthor("author2"));
        System.out.println(repository.findByName("course3"));

    }

}
