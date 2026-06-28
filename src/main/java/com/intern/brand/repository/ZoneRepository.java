package com.intern.brand.repository;

import com.intern.brand.model.Brand;
import com.intern.brand.model.Zone;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ZoneRepository extends JpaRepository<Zone, Long> {
    List<Zone> findByBrand(Brand brand);
    long countByBrand(Brand brand);
    boolean existsByBrand(Brand brand);
}
