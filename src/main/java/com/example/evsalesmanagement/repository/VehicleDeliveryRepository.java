package com.example.evsalesmanagement.repository;

import com.example.evsalesmanagement.model.VehicleDelivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleDeliveryRepository extends JpaRepository<VehicleDelivery, Integer> {

    @Query("SELECT v FROM VehicleDelivery v WHERE LOWER(v.status) = LOWER('Pending')")
    List<VehicleDelivery> findPendingDeliveries();

    @Query("SELECT v FROM VehicleDelivery v WHERE LOWER(v.status) = LOWER('In Progress')")
    List<VehicleDelivery> findInProgressDeliveries();

    @Query("SELECT v FROM VehicleDelivery v WHERE LOWER(v.status) = LOWER('Completed')")
    List<VehicleDelivery> findCompletedDeliveries();
}
