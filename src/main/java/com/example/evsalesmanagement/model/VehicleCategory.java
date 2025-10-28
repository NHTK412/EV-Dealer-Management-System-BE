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

//DanhMucXe = VehicleCategory
@Entity
@Table(name = "VehicleCategory")
public class VehicleCategory extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "VehicleCategoryId")
    private Integer vehicleCategoryId;

    @Column(name = "VehicleCategoryName", nullable = false)
    private String vehicleCategoryName;

    @Column(name = "Description")
    private String description;

    @ManyToMany
    @JoinTable(name = "VehicleType_VehicleCategory", joinColumns = @JoinColumn(name = "VehicleCategoryId"), inverseJoinColumns = @JoinColumn(name = "VehicleTypeId"))

    private List<VehicleType> vehicleTypes = new ArrayList<>();

    public Integer getVehicleCategoryId() {
        return vehicleCategoryId;
    }

    public void setVehicleCategoryId(Integer vehicleCategoryId) {
        this.vehicleCategoryId = vehicleCategoryId;
    }

    public String getVehicleCategoryName() {
        return vehicleCategoryName;
    }

    public void setVehicleCategoryName(String vehicleCategoryName) {
        this.vehicleCategoryName = vehicleCategoryName;

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
