package com.example.learnspringboot;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class courseController {
    @RequestMapping("/courses")

    public List<Course> retrieveAllCourses() {
        return Arrays.asList(new Course(1, "Course1", "author1"),
                new Course(2, "course2", "author2"),

                new Course(3, "course3", "author3"),
                new Course(4, "course4", "author4"));
    }

}
