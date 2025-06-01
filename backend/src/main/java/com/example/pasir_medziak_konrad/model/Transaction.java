package com.example.pasir_medziak_konrad.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import jakarta.persistence.*;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "transactions")

public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private Double amount;

    @Enumerated(EnumType.STRING)
    private TransactionType type;

    private String tags;
    private String notes;
    private LocalDate timeStamp;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    // kontruktory

    public Transaction(Double amount, TransactionType type, String tags, String notes,User user) {
        this.amount=amount;
        this.type=type;
        this.tags=tags;
        this.notes=notes;
        this.timeStamp=LocalDate.now();
        this.user=user;
    }

    /**

    public void amount(double Amount){
        this.amount=Amount;
    }
    public void type(TransactionType type){
        this.type=type;
    }

    public void tags(String tags){
        this.tags=tags;
    }

    public void notes(String notes){
        this.notes=notes;
    }

    public void timeStamp(LocalDate timeStamp){
        this.timeStamp=timeStamp;
    }

    // settery

    public void setId(Long id) {
        this.id=id;
    }

    public void setAmount(double amount) {
        this.amount=amount;
    }

    public void setType(TransactionType type) {
        this.type=type;
    }

    public void setTags(String tags) {
        this.tags=tags;
    }

    public void setNotes(String notes) {
        this.notes=notes;
    }

    // TU MOŻE CZEGOŚ BRAKOWAĆ - DO SPRAWDZENIA JAK COŚ SPADNIE Z ROWERKA

    //Gettery

    public Long getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public TransactionType getType() {
        return type;
    }

    public String getTags() {
        return tags;
    }

    public String getNotes() {
        return notes;
    }

    public LocalDate getTimeStamp() {
        return timeStamp;
    }
     */

}
