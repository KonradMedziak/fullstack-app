package com.example.pasir_medziak_konrad.repository;


import com.example.pasir_medziak_konrad.model.Membership;
import com.example.pasir_medziak_konrad.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MembershipRepository extends JpaRepository<Membership, Long> {
    List<Membership> findByGroupId(Long groupId);
}
