package com.example.pasir_medziak_konrad.service;

import com.example.pasir_medziak_konrad.dto.membershipDTO;
import com.example.pasir_medziak_konrad.model.Group;
import com.example.pasir_medziak_konrad.model.Membership;
import com.example.pasir_medziak_konrad.model.User;
import com.example.pasir_medziak_konrad.repository.GroupRepository;
import com.example.pasir_medziak_konrad.repository.GroupRepository;
import com.example.pasir_medziak_konrad.repository.MembershipRepository;
import com.example.pasir_medziak_konrad.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MembershipService {

    private final MembershipRepository membershipRepository;
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;

    public MembershipService(MembershipRepository membershipRepository, GroupRepository groupRepository, UserRepository userRepository) {
        this.membershipRepository = membershipRepository;
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
    }

    public List<Membership> getGroupMembers(Long groupId) {
        return membershipRepository.findByGroupId(groupId);
    }

    public Membership addMember(membershipDTO membershipDTO) {
        User user = userRepository.findByEmail(membershipDTO.getUserEmail()).orElseThrow(()->new EntityNotFoundException("Nie znaleziono użytkownika o emailu: "+membershipDTO.getUserEmail()));

        Group group = groupRepository.findById(membershipDTO.getGroupId()).orElseThrow(()->new EntityNotFoundException("Nie znaleziono grupy o ID: "+membershipDTO.getGroupId()));

        boolean alreadyMember = membershipRepository.findByGroupId(group.getId()).stream().anyMatch(membership->membership.getUser().getId().equals(user.getId()));

        if (alreadyMember) {
            throw new IllegalStateException("Użytkownik jest już członkiem tej grupy.");
        }

        Membership membership = new Membership();
        membership.setUser(user);
        membership.setGroup(group);
        return membershipRepository.save(membership);
    }

    public void removeMember(Long membershipId) {
        Membership membership = membershipRepository.findById(membershipId).orElseThrow(()-> new EntityNotFoundException("Członkowstwo nie istnieje"));

        User currentUser = getCurrentUser();
        User groupOwner = membership.getGroup().getOwner();

        if(!currentUser.equals(groupOwner)) {
            throw new SecurityException("Tylko własciciel grupy może usuwać członków.");
        }

    }
    public User getCurrentUser(){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email).orElseThrow(()->new EntityNotFoundException("Nie znaleziono użytkownika: "+email));
    }
}
