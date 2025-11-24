package com.example.evsalesmanagement.repository;

import java.lang.foreign.Linker.Option;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.evsalesmanagement.model.WarehouseReleaseNote;

@Repository
public interface WarehouseReleaseNoteRepository  extends JpaRepository<WarehouseReleaseNote, Integer> {

    Optional<WarehouseReleaseNote> findByOrderId(Integer orderId);
}
