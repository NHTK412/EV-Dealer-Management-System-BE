package com.example.evsalesmanagement.repository;

// import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.evsalesmanagement.model.AgencyWholesalePrice;

@Repository
public interface AgencyWholesalePriceRepository extends JpaRepository<AgencyWholesalePrice, Integer> {

    List<AgencyWholesalePrice> findByAgency_AgencyIdAndVehicleTypeDetail_VehicleTypeDetailIdIn(
            Integer agencyId,
            Set<Integer> vehicleTypeDetailIds);

}