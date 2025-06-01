package com.example.pasir_medziak_konrad.controler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
    @RestController
    public class infoController {
        @GetMapping("/api/info")
        public String info() {
            return " \"appName\":\"Aplikacja Budżetowa\",\n" +
                    "    \"version\":\"1.0\",\n" +
                    "    \"message\":\"Witaj w aplikacji budżetowej stworzonej ze Spring Boot!\"";
        }
    }

