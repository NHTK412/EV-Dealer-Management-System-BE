package com.example.evsalesmanagement.model;

import java.util.List;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "VehicleType")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VehicleType extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "VehicleTypeId")
    private Integer vehicleTypeId;

    @Column(name = "VehicleTypeName", nullable = false)
    private String vehicleTypeName;

    @Column(name = "ManufactureYear")
    private Integer manufactureYear;

    @Column(name = "Description")
    private String description;

    @OneToMany(mappedBy = "vehicleType", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<VehicleTypeDetail> vehicleTypeDetails;


}
