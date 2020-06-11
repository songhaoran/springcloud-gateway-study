package com.song.operate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class OperateApplication {

    public static void main(String[] args) {
        SpringApplication.run(OperateApplication.class, args);
    }


    @GetMapping("/operate/test1")
    public String test1() {
        return "operate-test1";
    }
}
