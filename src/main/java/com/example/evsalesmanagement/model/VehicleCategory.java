package com.example.evsalesmanagement.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "VehicleCategory")
public class VehicleCategory extends GhiNhanThoiGian {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "VehicleCategoryId")
    private Integer vehicleCategoryId;

    @Column(name = "CategoryName", nullable = false)
    private String categoryName;

    @Column(name = "Description")
    private String description;

    @ManyToMany
    @JoinTable(
        name = "VehicleType_VehicleCategory",
        joinColumns = @JoinColumn(name = "VehicleCategoryId"),
        inverseJoinColumns = @JoinColumn(name = "VehicleTypeId")
    )
    private List<VehicleType> vehicleTypes = new ArrayList<>();

    public Integer getVehicleCategoryId() {
        return vehicleCategoryId;
    }

    public void setVehicleCategoryId(Integer vehicleCategoryId) {
        this.vehicleCategoryId = vehicleCategoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
