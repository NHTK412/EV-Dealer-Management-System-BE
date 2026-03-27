package com.example.evsalesmanagement.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.evsalesmanagement.enums.CategoryStatusEnum;
import com.example.evsalesmanagement.model.VehicleCategory;

@Repository
public interface CategoryRepository extends JpaRepository<VehicleCategory, Integer> {

    List<VehicleCategory> findByStatus(CategoryStatusEnum status);

    List<VehicleCategory> findByVehicleTypes_VehicleTypeId(Integer vehicleTypeId);

    Optional<VehicleCategory> findByVehicleCategoryIdAndStatus(Integer vehicleCategoryId, CategoryStatusEnum status);

}
