package com.example.evsalesmanagement.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "VehicleTypeDetail")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VehicleTypeDetail extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "VehicleTypeDetailId")
    private Integer vehicleTypeDetailId;

    @Column(name = "VehicleImage")
    private String vehicleImage;

    // cau hinh = configuration
    @Column(name = "Configuration")
    private String configuration;

    @Column(name = "Color")
    private String color;

    @Column(name = "Version")
    private String version;

    // tinh nang = features
    @Column(name = "Features")
    private String features;

    @Column(name = "Price")
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "VehicleTypeId")
    private VehicleType vehicleType;

}