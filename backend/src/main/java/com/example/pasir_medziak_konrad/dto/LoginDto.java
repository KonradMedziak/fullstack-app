package com.example.pasir_medziak_konrad.dto;

import jakarta.validation.Constraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Setter;
import lombok.Getter;

@Getter
@Setter
public class LoginDto {

    @NotNull(message = "Login nie może być pusty")
    private String email;

    @NotNull(message = "Hasło nie może być puste")
    private String password;
}
