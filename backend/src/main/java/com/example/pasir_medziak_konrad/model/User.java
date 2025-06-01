package com.example.pasir_medziak_konrad.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import lombok.Generated;
import lombok.Setter;
import lombok.Getter;

@Getter
@Setter
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "nazwa użytkownika jest wymagana")
    private String username;

    @Email(message = "podaj poprawny adres e-mail")
    private String email;

    @NotBlank(message = "hasło nie może być puste")
    private String password;

    private String currency = "PLN";
}
