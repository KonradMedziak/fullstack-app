package com.example.pasir_medziak_konrad.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;



@Data
public class balanceDTO {


    @NotBlank
    private double totalIncome;
    @NotBlank
    private double totalExpense;
    @NotBlank
    private double balance;

    @Builder
    public balanceDTO(double totalIncome, double totalExpense, double balance) {
        this.totalIncome = totalIncome;
        this.totalExpense = totalExpense;
        this.balance = balance;
    }
}
