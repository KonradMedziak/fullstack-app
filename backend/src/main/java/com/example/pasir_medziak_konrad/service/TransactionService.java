
package com.example.pasir_medziak_konrad.service;



import com.example.pasir_medziak_konrad.dto.TransactionDTO;
import com.example.pasir_medziak_konrad.dto.balanceDTO;
import com.example.pasir_medziak_konrad.model.Transaction;
import com.example.pasir_medziak_konrad.model.TransactionType;
import com.example.pasir_medziak_konrad.model.User;
import com.example.pasir_medziak_konrad.repository.TransactionRepository;
import com.example.pasir_medziak_konrad.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    public TransactionService(TransactionRepository transactionRepository,UserRepository userRepository) {
        this.transactionRepository=transactionRepository;
        this.userRepository=userRepository;
    }

    public List<Transaction> getAllTransactions(){
        User user=getCurrentUser();
        return transactionRepository.findAllByUser(user);
    }

    public Transaction getTransactionById(Long id) {
        return transactionRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Nie znaleziono transakcji o ID " + id));

    }

    public User getCurrentUser(){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email).orElseThrow(()->new EntityNotFoundException("Nie znaleziono zalogowanego użytkownika"));
    }

    public Transaction updateTransaction(Long id, TransactionDTO transactionDTO) {
        Transaction transaction = transactionRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Nie znaleziono transakcji o ID"+id));

        if(!transaction.getUser().getEmail().equals(getCurrentUser().getEmail())){
            throw new SecurityException("Brak dostępu do edycji tej transkacji");
        }

        transaction.setAmount(transactionDTO.getAmount());
        transaction.setType(TransactionType.valueOf(transactionDTO.getType()));
        transaction.setTags(transactionDTO.getTags());
        transaction.setNotes(transactionDTO.getNotes());
        return transactionRepository.save(transaction);
    }

    public Transaction createTransaction(TransactionDTO transactionDTO) {
        Transaction transaction=new Transaction();
        transaction.setAmount(transactionDTO.getAmount());
        transaction.setType(TransactionType.valueOf(transactionDTO.getType()));
        transaction.setTags(transactionDTO.getTags());
        transaction.setNotes(transactionDTO.getNotes());
        transaction.setUser(getCurrentUser());
        transaction.setTimeStamp(LocalDate.from(LocalDateTime.now()));
        return transactionRepository.save(transaction);
    }

    public Transaction deleteTransaction(TransactionDTO transactionDTO,Long id) {

        Transaction transaction=transactionRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Nie znaleziono transakcji o ID"+id));

        if(!transaction.getUser().getEmail().equals(getCurrentUser().getEmail())){
            throw new SecurityException("Brak dostępu do edycji tej transkacji");
        }

        transactionRepository.delete(transaction);
        return transaction;
    }

    public balanceDTO getUserBalance(User user) {
        List<Transaction> userTransactions = transactionRepository.findAllByUser((user));

        double income = userTransactions.stream()
                .filter(t->t.getType()==TransactionType.INCOME)
                .mapToDouble(Transaction::getAmount)
                .sum();

        double expense = userTransactions.stream()
                .filter(t->t.getType()==TransactionType.EXPENSE)
                .mapToDouble(Transaction::getAmount)
                .sum();

        return new balanceDTO(income,expense,income-expense);

    }

}
