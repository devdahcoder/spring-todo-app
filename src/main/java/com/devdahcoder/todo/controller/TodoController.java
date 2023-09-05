package com.devdahcoder.todo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/todo")
public class TodoController {

    @GetMapping("")
    public String findAllTodo() {

        return "Hello World";

    }

}