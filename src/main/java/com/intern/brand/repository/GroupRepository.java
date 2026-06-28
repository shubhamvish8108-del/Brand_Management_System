package com.intern.brand.repository;

import com.intern.brand.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface GroupRepository extends JpaRepository<Group, Long> {
    Optional<Group> findByGroupNameIgnoreCase(String groupName);
    boolean existsByGroupNameIgnoreCase(String groupName);
    List<Group> findByIsActiveTrue();
}
