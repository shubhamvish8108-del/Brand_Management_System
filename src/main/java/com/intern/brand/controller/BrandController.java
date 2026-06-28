package com.intern.brand.controller;
import java.util.List;

import com.intern.brand.model.Brand;
import com.intern.brand.model.Chain;
import com.intern.brand.model.Group;
import com.intern.brand.service.BrandService;
import com.intern.brand.service.ChainService;
import com.intern.brand.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @Autowired
    private ChainService chainService;

    @Autowired
    private GroupService groupService;

    @GetMapping
    public String dashboard(
            @RequestParam(required = false) String filterCompany,
            @RequestParam(required = false) String filterGroup,
            Model model) {

        List<Brand> brands;
        if (filterCompany != null && !filterCompany.isEmpty()) {
            brands = brandService.getBrandsByCompanyName(filterCompany);
            model.addAttribute("filterCompany", filterCompany);
        } else if (filterGroup != null && !filterGroup.isEmpty()) {
            brands = brandService.getBrandsByGroupName(filterGroup);
            model.addAttribute("filterGroup", filterGroup);
        } else {
            brands = brandService.getAllBrandsWithDetails();
        }

        model.addAttribute("brands", brands);
        model.addAttribute("totalGroups", groupService.countAllGroups());
        model.addAttribute("totalChains", chainService.countAllChains());
        model.addAttribute("totalBrands", brandService.countAllBrands());
        model.addAttribute("chains", chainService.getAllChains());
        model.addAttribute("groups", groupService.getAllGroups());
        return "dashboard";
    }

    @GetMapping("/brands/new")
    public String showAddBrandForm(Model model) {
        model.addAttribute("chains", chainService.getActiveChains());
        return "brand-form";
    }

    @PostMapping("/brands")
    public String createBrand(@RequestParam String brandName, @RequestParam Long chainId, Model model) {
        try {
            brandService.createBrand(brandName, chainId);
            return "redirect:/?success=Brand added successfully!";
        } catch (IllegalArgumentException e) {
            model.addAttribute("chains", chainService.getActiveChains());
            model.addAttribute("error", e.getMessage());
            model.addAttribute("brandName", brandName);
            return "brand-form";
        }
    }

    @GetMapping("/brands/{id}/edit")
    public String showEditBrandForm(@PathVariable Long id, Model model) {
        Brand brand = brandService.getBrandById(id)
                .orElseThrow(() -> new IllegalArgumentException("Brand not found!"));
        model.addAttribute("brand", brand);
        model.addAttribute("chains", chainService.getActiveChains());
        return "brand-form";
    }

    @PostMapping("/brands/{id}")
    public String updateBrand(@PathVariable Long id, @RequestParam String brandName, @RequestParam Long chainId, Model model) {
        try {
            brandService.updateBrand(id, brandName, chainId);
            return "redirect:/?success=Brand updated successfully!";
        } catch (IllegalArgumentException e) {
            model.addAttribute("brand", brandService.getBrandById(id).orElse(null));
            model.addAttribute("chains", chainService.getActiveChains());
            model.addAttribute("error", e.getMessage());
            return "brand-form";
        }
    }

    @PostMapping("/brands/{id}/deactivate")
    public String deactivateBrand(@PathVariable Long id) {
        brandService.deactivateBrand(id);
        return "redirect:/?success=Brand deactivated!";
    }

    @PostMapping("/brands/{id}/activate")
    public String activateBrand(@PathVariable Long id) {
        brandService.activateBrand(id);
        return "redirect:/?success=Brand activated!";
    }

    @PostMapping("/brands/{id}/delete")
    public String deleteBrand(@PathVariable Long id) {
        try {
            if (brandService.canDeleteBrand(id)) {
                brandService.deleteBrand(id);
                return "redirect:/?success=Brand deleted!";
            } else {
                return "redirect:/?error=Cannot delete! Brand is linked to zones.";
            }
        } catch (Exception e) {
            return "redirect:/?error=" + e.getMessage();
        }
    }
}
