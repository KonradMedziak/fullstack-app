package com.example.pasir_medziak_konrad.controler;

import com.example.pasir_medziak_konrad.dto.TransactionDTO;
import com.example.pasir_medziak_konrad.model.Transaction;
import com.example.pasir_medziak_konrad.repository.TransactionRepository;
import com.example.pasir_medziak_konrad.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public ResponseEntity<List<Transaction>> getAllTransactions(){
        return ResponseEntity.ok(transactionService.getAllTransactions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable Long id){
        return ResponseEntity.ok(transactionService.getTransactionById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Transaction> updateTransaction(
            @PathVariable Long id,
            @Valid @RequestBody TransactionDTO transactionDTO){
        Transaction updatedTransaction = transactionService.updateTransaction(id, transactionDTO);
        return ResponseEntity.ok(updatedTransaction);
    }

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@Valid @RequestBody TransactionDTO transactionDTO){
        Transaction createdTransaction = transactionService.createTransaction(transactionDTO);
        return ResponseEntity.ok(createdTransaction);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Transaction> deleteTransaction(@PathVariable TransactionDTO transactionDTO, Long id) {
        transactionService.deleteTransaction(transactionDTO,id);
        return ResponseEntity.ok().build();
    }



    /**
    @Autowired
    private TransactionRepository transactionRepository;


    @GetMapping
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        List<Transaction> transactions = transactionRepository.findAll();
        return ResponseEntity.ok(transactions);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Transaction> updateTransaction(@PathVariable Long id, @RequestBody Transaction transactionDetails) {
        Transaction transaction = transactionRepository.findById(id).orElseThrow(()->new RuntimeException("Transaction not found with id "+id));

        transaction.setAmount(transactionDetails.getAmount());
        transaction.setType(transactionDetails.getType());
        transaction.setTags(transactionDetails.getTags());
        transaction.setNotes(transactionDetails.getNotes());

        Transaction upadteTransaction = transactionRepository.save(transaction);
        return ResponseEntity.ok(upadteTransaction);
    }

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transactionDetails) {
        Transaction transaction = transactionRepository.save(transactionDetails);
        return ResponseEntity.ok(transaction);
    }
*/


}
