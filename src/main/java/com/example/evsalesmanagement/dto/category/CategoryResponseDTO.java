package com.example.evsalesmanagement.dto.category;

import com.example.evsalesmanagement.enums.CategoryStatusEnum;
import com.example.evsalesmanagement.model.VehicleCategory;

public class CategoryResponseDTO {

    private Integer categoryId;

    private String categoryName;

    private CategoryStatusEnum status;

    private String description;

    public CategoryResponseDTO() {
    }

    public CategoryResponseDTO(VehicleCategory category) {
        this.categoryId = category.getVehicleCategoryId();
        this.categoryName = category.getVehicleCategoryName();
        this.status = category.getStatus();
        this.description = category.getDescription();
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public CategoryStatusEnum getStatus() {
        return status;
    }

    public void setStatus(CategoryStatusEnum status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
