package com.example.firstapp.todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service //Service katmanı, uygulamanın ana işlemlerinin gerçekleştirildiği yerdir.
public class todoService {
    private static List<todo> todos = new ArrayList<>();

    //if you are adding smth in list, first initialize it.
    static {
        todos.add(new todo(1, "user1", "mission1", LocalDate.now().plusYears(1), false));
        todos.add(new todo(2, "user1", "mission2", LocalDate.now().plusYears(2), false));
        todos.add(new todo(3, "user1", "mission3", LocalDate.now().plusYears(3), false));

    }

    public List<todo> findByUsername(String username) {

        return todos;
    }
}
