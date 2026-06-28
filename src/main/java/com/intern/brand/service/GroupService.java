package com.intern.brand.service;

import com.intern.brand.model.Group;
import com.intern.brand.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    public List<Group> getAllGroups() { return groupRepository.findAll(); }
    public List<Group> getActiveGroups() { return groupRepository.findByIsActiveTrue(); }
    public Optional<Group> getGroupById(Long id) { return groupRepository.findById(id); }

    public Group createGroup(String groupName) {
        if (groupRepository.existsByGroupNameIgnoreCase(groupName)) {
            throw new IllegalArgumentException("Group name already exists!");
        }
        return groupRepository.save(new Group(groupName));
    }

    public void deactivateGroup(Long id) {
        Group group = groupRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Group not found!"));
        group.setIsActive(false);
        groupRepository.save(group);
    }

    public void activateGroup(Long id) {
        Group group = groupRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Group not found!"));
        group.setIsActive(true);
        groupRepository.save(group);
    }

    public long countAllGroups() { return groupRepository.count(); }
}
