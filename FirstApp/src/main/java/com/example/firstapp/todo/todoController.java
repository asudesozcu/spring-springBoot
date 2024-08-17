package com.example.firstapp.todo;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller //Controller sınıfı genellikle view (görünüm) katmanıyla etkileşimde bulunur.
@SessionAttributes("username1") //to be able reach username1 .username1'in geçtiği yer yere koy sonrasında
public class todoController {

    public todoController(com.example.firstapp.todo.todoService todoService) {
        this.todoService = todoService;
    }

    private todoService todoService;

    @RequestMapping("list-todos")
    public String returnAllTodos(ModelMap model) {
        List<todo> todos = todoService.findByUsername("user1");
        model.addAttribute("todos", todos);
        return "listTodos";
    }

}
