package com.song.query;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@SpringBootApplication
@RestController
public class QueryApplication {

    public static void main(String[] args) {
        SpringApplication.run(QueryApplication.class, args);
    }


    @GetMapping("/query/test1")
    public String test1(HttpServletRequest request) {
        String token = request.getHeader("token");
        System.out.println("token:" + token);
        return "query-test1";
    }
}
