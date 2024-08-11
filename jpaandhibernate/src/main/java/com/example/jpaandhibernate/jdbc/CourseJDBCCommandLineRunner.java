package com.example.jpaandhibernate.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.jpaandhibernate.Course;

@Component
public class CourseJDBCCommandLineRunner implements CommandLineRunner {
    @Autowired
    private CourseJDBCRepo repository;

    @Override
    public void run(String... args) throws Exception {
        repository.insert(new Course("course1", "author1", 1));
        repository.insert(new Course("course2", "author2", 2));
        repository.insert(new Course("course3", "author3", 3));
        // repository.deleteById(1);
        // System.out.println(repository.findById(2));
        // System.out.println(repository.findById(3));

    }

}
