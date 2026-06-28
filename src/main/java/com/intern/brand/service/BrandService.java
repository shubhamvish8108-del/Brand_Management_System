package com.intern.brand.service;

import com.intern.brand.model.Brand;
import com.intern.brand.model.Chain;
import com.intern.brand.repository.BrandRepository;
import com.intern.brand.repository.ChainRepository;
import com.intern.brand.repository.ZoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BrandService {

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private ChainRepository chainRepository;

    @Autowired
    private ZoneRepository zoneRepository;

    public List<Brand> getAllBrandsWithDetails() { return brandRepository.findAllWithChainAndGroup(); }
    public List<Brand> getAllBrands() { return brandRepository.findAll(); }
    public Optional<Brand> getBrandById(Long id) { return brandRepository.findById(id); }

    public List<Brand> getBrandsByCompanyName(String companyName) {
        return chainRepository.findAll().stream()
                .filter(c -> c.getCompanyName().equalsIgnoreCase(companyName))
                .findFirst()
                .map(c -> brandRepository.findByChainChainId(c.getChainId()))
                .orElse(List.of());
    }

    public List<Brand> getBrandsByGroupName(String groupName) {
        return brandRepository.findAllWithChainAndGroup().stream()
                .filter(b -> b.getChain().getGroup().getGroupName().equalsIgnoreCase(groupName))
                .toList();
    }

    public Brand createBrand(String brandName, Long chainId) {
        Chain chain = chainRepository.findById(chainId)
                .orElseThrow(() -> new IllegalArgumentException("Chain not found!"));
        if (brandRepository.existsByBrandNameIgnoreCaseAndChain(brandName, chain)) {
            throw new IllegalArgumentException("Brand name already exists for this company!");
        }
        return brandRepository.save(new Brand(brandName, chain));
    }

    public Brand updateBrand(Long id, String brandName, Long chainId) {
        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Brand not found!"));
        Chain newChain = chainRepository.findById(chainId)
                .orElseThrow(() -> new IllegalArgumentException("Chain not found!"));
        brand.setBrandName(brandName);
        brand.setChain(newChain);
        return brandRepository.save(brand);
    }

    public void deactivateBrand(Long id) {
        Brand brand = brandRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Brand not found!"));
        brand.setIsActive(false);
        brandRepository.save(brand);
    }

    public void activateBrand(Long id) {
        Brand brand = brandRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Brand not found!"));
        brand.setIsActive(true);
        brandRepository.save(brand);
    }

    public boolean canDeleteBrand(Long id) {
        Brand brand = brandRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Brand not found!"));
        return !zoneRepository.existsByBrand(brand);
    }

    public void deleteBrand(Long id) {
        if (!canDeleteBrand(id)) {
            throw new IllegalStateException("Cannot delete brand! Brand is linked to zones.");
        }
        brandRepository.deleteById(id);
    }

    public long countAllBrands() { return brandRepository.count(); }
    public long countActiveBrands() { return brandRepository.countByIsActiveTrue(); }
}
