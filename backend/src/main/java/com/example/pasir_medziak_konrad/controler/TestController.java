package com.example.pasir_medziak_konrad.controler;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/api/test")
    public String test() {
        return "Hello world!";
    }
}
