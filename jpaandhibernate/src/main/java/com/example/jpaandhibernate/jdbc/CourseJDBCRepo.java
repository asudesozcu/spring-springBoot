package com.example.jpaandhibernate.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.jpaandhibernate.Course;

@Repository // used when class talks to db
public class CourseJDBCRepo {

    @Autowired
    private JdbcTemplate springJdbcTemplate;

    private static String query = """
                  insert into course(id,name,author)
            values(?,?,?);

            """;

    private static String deleteQuery = """
            delete from course where id=?

                    """;

    private static String selectQuery = """
            select * from course where id = ?
            """;

    public void insert(Course course) {
        springJdbcTemplate.update(query, course.getId(), course.getName(), course.getAuthor());

    }

    public void deleteById(long id) {
        springJdbcTemplate.update(deleteQuery, id);
    }

    public Course findById(long id) {
        return springJdbcTemplate.queryForObject(selectQuery, new BeanPropertyRowMapper<>(Course.class), id);
        // BeanPropertyRowMapper : resultset to Bean. helps to map.
    }
}
