package com.example.learnspringboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyServiseController {
    @Autowired
    private CurrencyServiseConfiguration configuration;

    @RequestMapping("/currency-configuration")
    public CurrencyServiseConfiguration retrieveAllCourses() {
        return configuration;
    }
}
