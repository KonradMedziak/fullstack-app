package com.example.pasir_medziak_konrad.controler;

import com.example.pasir_medziak_konrad.dto.TransactionDTO;
import com.example.pasir_medziak_konrad.dto.balanceDTO;
import com.example.pasir_medziak_konrad.model.Transaction;
import com.example.pasir_medziak_konrad.model.User;
import com.example.pasir_medziak_konrad.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import java.util.List;

@Controller
public class TransactionGraphQLController {

    private final TransactionService transactionService;

    public TransactionGraphQLController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @QueryMapping
    public List<Transaction> transactions() {
        return transactionService.getAllTransactions();
    }


    @MutationMapping
    public Transaction addTransaction(@Valid @Argument TransactionDTO transactionDTO) {
        return transactionService.createTransaction(transactionDTO);

    }

    @MutationMapping
    public Transaction updateTransaction(@Argument Long id,@Valid @Argument TransactionDTO transactionDTO) {
        return transactionService.updateTransaction(id,transactionDTO);
    }

    @MutationMapping
    public Boolean deleteTransaction(@Valid @Argument TransactionDTO transactionDTO,@Argument Long id) {
         transactionService.deleteTransaction(transactionDTO,id);
        return true;
    }

    @QueryMapping
    public balanceDTO userBalance(){
        User user = transactionService.getCurrentUser();
        return transactionService.getUserBalance(user);
    }
}
