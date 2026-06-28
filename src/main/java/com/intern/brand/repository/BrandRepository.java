package com.intern.brand.repository;

import com.intern.brand.model.Brand;
import com.intern.brand.model.Chain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface BrandRepository extends JpaRepository<Brand, Long> {
    List<Brand> findByChain(Chain chain);
    List<Brand> findByIsActiveTrue();
    List<Brand> findByChainAndIsActiveTrue(Chain chain);
    List<Brand> findByChainChainId(Long chainId);
    boolean existsByBrandNameIgnoreCaseAndChain(String brandName, Chain chain);
    @Query("SELECT b FROM Brand b JOIN FETCH b.chain c JOIN FETCH c.group")
    List<Brand> findAllWithChainAndGroup();
    long countByIsActiveTrue();
}
