package com.example.jpaandhibernate.jpa;

import org.springframework.stereotype.Repository;

import com.example.jpaandhibernate.Course;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class CourseJPARepo {
    @PersistenceContext // more spesific annotation than Autorwired . used with entity manager
    private EntityManager entityManager;

    public void  insert(Course course) {

        entityManager.merge(course);
    }

    public Course FindbyId(long id) {
        return entityManager.find(Course.class, id);
    }

    public void deleteById(long id) {
        Course course = entityManager.find(Course.class, id);
        entityManager.remove(course);
    }
}
