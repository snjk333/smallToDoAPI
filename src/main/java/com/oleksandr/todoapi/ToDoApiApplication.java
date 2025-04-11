package com.oleksandr.todoapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class ToDoApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ToDoApiApplication.class, args);
    }

}
