package com.example.pasir_medziak_konrad.repository;


import com.example.pasir_medziak_konrad.model.Group;
import com.example.pasir_medziak_konrad.model.Transaction;
import com.example.pasir_medziak_konrad.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    List<Group> findByMemberships_User(User user);
}
