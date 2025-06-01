package com.example.pasir_medziak_konrad.service;


import com.example.pasir_medziak_konrad.dto.GroupTransactionDTO;
import com.example.pasir_medziak_konrad.model.Debt;
import com.example.pasir_medziak_konrad.model.Group;
import com.example.pasir_medziak_konrad.model.Membership;
import com.example.pasir_medziak_konrad.model.User;
import com.example.pasir_medziak_konrad.repository.DebtRepository;
import com.example.pasir_medziak_konrad.repository.GroupRepository;
import com.example.pasir_medziak_konrad.repository.MembershipRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class GroupTransactionService {
    private final GroupRepository groupRepository;
    private final MembershipRepository membershipRepository;
    private final DebtService debtService;
    private final DebtRepository debtRepository;


    public GroupTransactionService(GroupRepository groupRepository, MembershipRepository membershipRepository, DebtService debtService, DebtRepository debtRepository) {
        this.groupRepository = groupRepository;
        this.membershipRepository = membershipRepository;
        this.debtService = debtService;
        this.debtRepository = debtRepository;
    }

    public void addGroupTransaction(GroupTransactionDTO dto, User currentUser){
        Group group = groupRepository.findById(dto.getGroupId()).orElseThrow(()->new EntityNotFoundException("Nie znaleziono grupy"));

        List<Membership> members = membershipRepository.findByGroupId(group.getId());
        List<Long> selectedUserIds = dto.getSelectedUserIds();

        if(selectedUserIds == null){
            throw new IllegalArgumentException("Nie wybrano żadnych użytkowników");
        }


        double amountPerUser = dto.getAmount() / members.size();

        for(Membership member : members){
            User debtor = member.getUser();

            if(!debtor.getId().equals(currentUser.getId()) && selectedUserIds.contains(debtor.getId())){
                Debt debt = new Debt();
                debt.setDebtor(debtor);
                debt.setCreditor(currentUser);
                debt.setGroup(group);
                debt.setAmount(amountPerUser);
                debt.setTitle(dto.getTitle());
                debtRepository.save(debt);
            }
        }
    }
}
