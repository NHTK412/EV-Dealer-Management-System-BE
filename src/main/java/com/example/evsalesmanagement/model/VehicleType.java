package com.example.evsalesmanagement.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "VehicleType")
public class VehicleType extends TimeStampRecord{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "VehicleTypeId")
    private Integer vehicleTypeId;

    @Column(name = "VehicleTypeName", nullable = false)
    private String vehicleTypeName;

    //nam san xuat = manufactureYear
    @Column(name = "ManufactureYear")
    private Integer manufactureYear;

    //mo ta = description
    @Column(name = "Description")
    private String description;

    public Integer getVehicleTypeId() {
        return vehicleTypeId;
    }

    public void setVehicleTypeId(Integer vehicleTypeId) {
        this.vehicleTypeId = vehicleTypeId;
    }

    public String getVehicleTypeName() {
        return vehicleTypeName;
    }

    public void setVehicleTypeName(String vehicleTypeName) {
        this.vehicleTypeName = vehicleTypeName;
    }

    public Integer getManufactureYear() {
        return manufactureYear;
    }

    public void setManufactureYear(Integer manufactureYear) {
        this.manufactureYear = manufactureYear;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // @ManyToMany(mappedBy = "loaiXes")
    // private List<DanhMucXe> danhMucXes = new ArrayList<>();

    // @OneToMany(mappedBy = "loaiXe")
    // private List<ChiTietLoaiXe> chiTietLoaiXes = new ArrayList<>();

    
    
}