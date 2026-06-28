package com.intern.brand.repository;

import com.intern.brand.model.Chain;
import com.intern.brand.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface ChainRepository extends JpaRepository<Chain, Long> {
    List<Chain> findByGroup(Group group);
    List<Chain> findByIsActiveTrue();
    List<Chain> findByGroupAndIsActiveTrue(Group group);
    boolean existsByCompanyNameIgnoreCase(String companyName);
    List<Chain> findByGroupGroupId(Long groupId);
}
