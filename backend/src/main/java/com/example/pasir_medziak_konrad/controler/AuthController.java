package com.example.pasir_medziak_konrad.controler;

import com.example.pasir_medziak_konrad.dto.LoginDto;
import com.example.pasir_medziak_konrad.dto.UserDto;
import com.example.pasir_medziak_konrad.model.User;
import com.example.pasir_medziak_konrad.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody @Valid UserDto Dto) {
        return ResponseEntity.ok(userService.register(Dto));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto dto) {
        try{
            String token = userService.login(dto);
            return ResponseEntity.ok(Map.of("token", token));
        } catch (UsernameNotFoundException | BadCredentialsException ex){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
        }
    }
}
