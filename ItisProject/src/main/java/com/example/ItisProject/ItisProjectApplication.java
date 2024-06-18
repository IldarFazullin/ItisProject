package com.example.ItisProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ItisProjectApplication {
    public static void main(String[] args) {
        SpringApplication.run(ItisProjectApplication.class, args);
        System.out.println("http://localhost:8099/");
    }
}
