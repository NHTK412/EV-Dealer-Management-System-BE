package com.example.evsalesmanagement.repository;

import com.example.evsalesmanagement.enums.VehicleDeliveryStatusEnum;
import com.example.evsalesmanagement.model.VehicleDelivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.evsalesmanagement.model.Order;

import java.util.List;

@Repository
public interface VehicleDeliveryRepository extends JpaRepository<VehicleDelivery, Integer> {

    boolean existsByOrderIdAndStatusIn(Order order, List<VehicleDeliveryStatusEnum> statuses);

    List<VehicleDelivery> findByStatus(VehicleDeliveryStatusEnum status);
    
}