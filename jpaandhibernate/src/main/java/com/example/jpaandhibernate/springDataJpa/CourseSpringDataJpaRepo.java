package com.example.jpaandhibernate.springDataJpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jpaandhibernate.Course;

public interface CourseSpringDataJpaRepo extends JpaRepository<Course, Long> { // type of the primary key.

    List<Course> findByAuthor(String author);

    List<Course> findByName(String name);

}
