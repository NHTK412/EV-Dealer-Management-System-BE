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
@Table(name = "VehicleDetail")
public class VehicleDetail extends GhiNhanThoiGian {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "VehicleTypeDetailId")
    private Integer vehicleDetailId;

    @Column(name = "VehicleImage")
    private String vehicleImage;

    @Column(name = "Configuration")
    private String configuration;

    @Column(name = "Color")
    private String color;

    @Column(name = "Version")
    private String version;

    @Column(name = "Feature")
    private String feature;

    @Column(name = "Price")
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "VehicleTypeId")
    private VehicleType vehicleType;

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

    public Integer getVehicleTypeDetailId() {
        return vehicleDetailId;
    }

    public void setVehicleTypeDetailId(Integer vehicleTypeDetailId) {
        this.vehicleDetailId = vehicleTypeDetailId;
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

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
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

    public String getVehicleImage() {
        return vehicleImage;
    }

    public void setVehicleImage(String vehicleImage) {
        this.vehicleImage = vehicleImage;
    }
}
