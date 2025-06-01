package com.example.pasir_medziak_konrad.dto;


import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;

@Getter
public class TransactionDTO {
    @Setter
    @NotNull(message="kwota nie może być pusta")
    @Min(value=1,message="Kwota musi być większa niż 0")
    private Double amount;

    @Setter
    @NotNull(message="Typ transakcji nie może być pusty")
    @Pattern(regexp = "INCOME|EXPENSE",message = "Pole przyjmuje tylko 2 wartości (INCOME/EXPENSE)")
    private String Type;

    @Setter
    @Size(max=50,message = "MAX długość tagu wynosi 50 znaków")
    private String tags;

    @Setter
    @Size(max=255,message = "MAX długość notatki wynosi 255 znaków")
    private String notes;
}
