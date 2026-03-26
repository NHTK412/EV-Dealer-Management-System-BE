package com.example.evsalesmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.evsalesmanagement.dto.category.CategoryRequestDTO;
import com.example.evsalesmanagement.dto.category.CategoryResponseDTO;
import com.example.evsalesmanagement.enums.CategoryStatusEnum;
import com.example.evsalesmanagement.service.CategoryService;
import com.example.evsalesmanagement.utils.ApiResponse;

import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PreAuthorize("hasAnyRole('EVM_STAFF', 'ADMIN')")
    @GetMapping
    public ResponseEntity<ApiResponse<List<CategoryResponseDTO>>> getAllCategories(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") @Positive Integer size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        List<CategoryResponseDTO> response = categoryService.getAllCategories(pageable);
        return ResponseEntity.ok(new ApiResponse<>(true, "Lấy danh sách danh mục thành công", response));
    }

    @PreAuthorize("hasAnyRole('EVM_STAFF', 'ADMIN')")
    @GetMapping("/status/{status}")
    public ResponseEntity<ApiResponse<List<CategoryResponseDTO>>> getCategoriesByStatus(
            @PathVariable CategoryStatusEnum status) {
        List<CategoryResponseDTO> response = categoryService.getCategoriesByStatus(status);
        return ResponseEntity
                .ok(new ApiResponse<>(true, "Lấy danh sách danh mục theo trạng thái thành công", response));
    }

    @PreAuthorize("hasAnyRole('EVM_STAFF', 'ADMIN')")
    @GetMapping("/{categoryId}")
    public ResponseEntity<ApiResponse<CategoryResponseDTO>> getCategoryById(@PathVariable Integer categoryId) {
        CategoryResponseDTO response = categoryService.getCategoryById(categoryId);
        return ResponseEntity.ok(new ApiResponse<>(true, "Lấy thông tin danh mục thành công", response));
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ApiResponse<CategoryResponseDTO>> createCategory(
            @RequestBody CategoryRequestDTO requestDTO) {
        CategoryResponseDTO response = categoryService.createCategory(requestDTO);
        return ResponseEntity.ok(new ApiResponse<>(true, "Tạo danh mục thành công", response));
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("/{categoryId}")
    public ResponseEntity<ApiResponse<CategoryResponseDTO>> updateCategory(
            @PathVariable Integer categoryId,
            @RequestBody CategoryRequestDTO requestDTO) {
        CategoryResponseDTO response = categoryService.updateCategory(categoryId, requestDTO);
        return ResponseEntity.ok(new ApiResponse<>(true, "Cập nhật danh mục thành công", response));
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiResponse<CategoryResponseDTO>> deleteCategory(@PathVariable Integer categoryId) {
        CategoryResponseDTO response = categoryService.deleteCategory(categoryId);
        return ResponseEntity.ok(new ApiResponse<>(true, "Xóa danh mục thành công", response));
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/{categoryId}/permanent")
    public ResponseEntity<ApiResponse<Void>> permanentDeleteCategory(@PathVariable Integer categoryId) {
        categoryService.permanentDeleteCategory(categoryId);
        return ResponseEntity.ok(new ApiResponse<>(true, "Xóa vĩnh viễn danh mục thành công", null));
    }

}
