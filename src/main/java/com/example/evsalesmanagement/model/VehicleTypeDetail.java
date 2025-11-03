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

@Entity
@Table(name = "VehicleTypeDetail")
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

    public Integer getVehicleTypeDetailId() {
        return vehicleTypeDetailId;
    }

    public void setVehicleTypeDetailId(Integer vehicleTypeDetailId) {
        this.vehicleTypeDetailId = vehicleTypeDetailId;
    }

    public String getVehicleImage() {
        return vehicleImage;
    }

    public void setVehicleImage(String vehicleImage) {
        this.vehicleImage = vehicleImage;
    }

    public String getConfiguration() {
        return configuration;
    }

    public void setConfiguration(String configuration) {
        this.configuration = configuration;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }


    // @OneToMany(mappedBy = "chiTietLoaiXe")
    // private List<Xe> xes = new ArrayList<>();

    // @OneToMany(mappedBy = "chiTietLoaiXe")
    // private List<ChiTietBaoGia> chiTietBaoGias = new ArrayList<>();

    // @ManyToMany(mappedBy = "chiTietLoaiXes")
    // private List<KhuyenMai> khuyenMais = new ArrayList<>();

    // @OneToMany(mappedBy = "chiTietLoaiXe")
    // private List<GiaSiDaiLy> giaSiDaiLys = new ArrayList<>();

    // @OneToMany(mappedBy = "chiTietLoaiXe")
    // private List<ChiTietYeuCau> chiTietYeuCaus = new ArrayList<>();

}