package com.intern.brand.service;

import com.intern.brand.model.Brand;
import com.intern.brand.model.Chain;
import com.intern.brand.repository.BrandRepository;
import com.intern.brand.repository.ChainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ChainService {

    @Autowired
    private ChainRepository chainRepository;

    @Autowired
    private BrandRepository brandRepository;

    public List<Chain> getAllChains() { return chainRepository.findAll(); }
    public List<Chain> getActiveChains() { return chainRepository.findByIsActiveTrue(); }
    public Optional<Chain> getChainById(Long id) { return chainRepository.findById(id); }
    public List<Chain> getChainsByGroupId(Long groupId) { return chainRepository.findByGroupGroupId(groupId); }

    public Chain createChain(String companyName, Long groupId, String gstNumber) {
        if (chainRepository.existsByCompanyNameIgnoreCase(companyName)) {
            throw new IllegalArgumentException("Company name already exists!");
        }
        Chain chain = new Chain();
        chain.setCompanyName(companyName);
        chain.setGstNumber(gstNumber);
        return chainRepository.save(chain);
    }

    public void deactivateChain(Long id) {
        Chain chain = chainRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Chain not found!"));
        chain.setIsActive(false);
        chainRepository.save(chain);
    }

    public void activateChain(Long id) {
        Chain chain = chainRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Chain not found!"));
        chain.setIsActive(true);
        chainRepository.save(chain);
    }

    public long countAllChains() { return chainRepository.count(); }
}
