package com.example.evsalesmanagement.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.evsalesmanagement.dto.category.CategoryRequestDTO;
import com.example.evsalesmanagement.dto.category.CategoryResponseDTO;
import com.example.evsalesmanagement.enums.CategoryStatusEnum;
import com.example.evsalesmanagement.exception.ResourceNotFoundException;
import com.example.evsalesmanagement.model.VehicleCategory;
import com.example.evsalesmanagement.repository.CategoryRepository;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Cacheable(value = "category-list", key = "#pageable")
    @Transactional
    public List<CategoryResponseDTO> getAllCategories(Pageable pageable) {
        Page<VehicleCategory> categories = categoryRepository.findAll(pageable);
        return categories.stream()
                .map(CategoryResponseDTO::new)
                .collect(Collectors.toList());
    }

    @Cacheable(value = "category-by-status", key = "#status")
    @Transactional
    public List<CategoryResponseDTO> getCategoriesByStatus(CategoryStatusEnum status) {
        List<VehicleCategory> categories = categoryRepository.findByStatus(status);
        return categories.stream()
                .map(CategoryResponseDTO::new)
                .collect(Collectors.toList());
    }

    @Cacheable(value = "category", key = "#categoryId")
    @Transactional
    public CategoryResponseDTO getCategoryById(Integer categoryId) {
        // VehicleCategory category = categoryRepository.findById(categoryId)
        VehicleCategory category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with ID: " + categoryId));
        return new CategoryResponseDTO(category);
    }

    @Transactional
    public CategoryResponseDTO createCategory(CategoryRequestDTO requestDTO) {
        VehicleCategory category = new VehicleCategory();
        category.setVehicleCategoryName(requestDTO.getCategoryName());
        category.setDescription(requestDTO.getDescription());

        if (requestDTO.getStatus() != null) {
            category.setStatus(requestDTO.getStatus());
        } else {
            category.setStatus(CategoryStatusEnum.ACTIVE);
        }

        VehicleCategory savedCategory = categoryRepository.save(category);
        return new CategoryResponseDTO(savedCategory);
    }

    @Caching(evict = {
            @CacheEvict(value = "category-list", allEntries = true),
            @CacheEvict(value = "category-by-status", allEntries = true)
    }, put = @CachePut(value = "category", key = "#categoryId"))
    @Transactional
    public CategoryResponseDTO updateCategory(Integer categoryId, CategoryRequestDTO requestDTO) {
        VehicleCategory category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with ID: " + categoryId));

        category.setVehicleCategoryName(requestDTO.getCategoryName());
        category.setDescription(requestDTO.getDescription());

        if (requestDTO.getStatus() != null) {
            category.setStatus(requestDTO.getStatus());
        }

        VehicleCategory updatedCategory = categoryRepository.save(category);
        return new CategoryResponseDTO(updatedCategory);
    }

    @Caching(evict = {
            @CacheEvict(value = "category-list", allEntries = true),
            @CacheEvict(value = "category-by-status", allEntries = true),
            @CacheEvict(value = "category", key = "#categoryId")
    })
    @Transactional
    public CategoryResponseDTO deleteCategory(Integer categoryId) {
        VehicleCategory category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with ID: " + categoryId));

        category.setStatus(CategoryStatusEnum.INACTIVE);
        VehicleCategory deletedCategory = categoryRepository.save(category);

        return new CategoryResponseDTO(deletedCategory);
    }

    @Caching(evict = {
            @CacheEvict(value = "category-list", allEntries = true),
            @CacheEvict(value = "category-by-status", allEntries = true),
            @CacheEvict(value = "category", key = "#categoryId")
    })
    @Transactional
    public void permanentDeleteCategory(Integer categoryId) {
        VehicleCategory category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with ID: " + categoryId));
        categoryRepository.delete(category);
    }

}
