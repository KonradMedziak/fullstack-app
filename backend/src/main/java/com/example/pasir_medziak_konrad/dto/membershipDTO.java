package com.example.pasir_medziak_konrad.dto;


import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class membershipDTO {
    @NotNull(message = "Email użytkownika nie może byc pusty")
    private String userEmail;

    @NotNull(message = "ID grupy nie może być pusty")
    private Long groupId;
}
