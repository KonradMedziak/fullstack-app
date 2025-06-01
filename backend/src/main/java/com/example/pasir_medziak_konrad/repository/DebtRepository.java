package com.example.pasir_medziak_konrad.repository;


import com.example.pasir_medziak_konrad.model.Debt;
import com.example.pasir_medziak_konrad.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DebtRepository extends JpaRepository<Debt, Long> {
    List<Debt> findByGroupId(Long groupId);
}
