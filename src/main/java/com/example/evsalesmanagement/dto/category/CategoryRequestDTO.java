package com.example.evsalesmanagement.dto.category;

import com.example.evsalesmanagement.enums.CategoryStatusEnum;

public class CategoryRequestDTO {

    private String categoryName;

    private CategoryStatusEnum status;

    private String description;

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
