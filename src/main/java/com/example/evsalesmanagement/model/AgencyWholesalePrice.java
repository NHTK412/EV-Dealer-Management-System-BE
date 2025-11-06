package com.example.evsalesmanagement.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.example.evsalesmanagement.enums.AgencyWholesalePriceStatusEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

//GiaSiDaiLy = AgencyWholesalePrice
@Entity
@Table(name = "AgencyWholesalePrice")
public class AgencyWholesalePrice extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AgencyWholesalePriceId")
    private Integer agencyWholesalePriceId;

    // GiaSi = WholesalePrice
    @Column(name = "WholesalePrice")
    private BigDecimal wholesalePrice;

    // SoLuongToiThieu = MinimumQuantity
    @Column(name = "MinimumQuantity")
    private Integer minimumQuantity;

    // ngaybatdau = StartDate
    @Column(name = "StartDate")
    private LocalDateTime startDate;

    // ngayketthuc = EndDate

    @Column(name = "EndDate")
    private LocalDateTime endDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "Status")
    private AgencyWholesalePriceStatusEnum status;

    @ManyToOne
    @JoinColumn(name = "AgencyId")
    private Agency agency;

    @ManyToOne
    @JoinColumn(name = "VehicleTypeDetailId")
    private VehicleTypeDetail vehicleTypeDetail;

    public Integer getAgencyWholesalePriceId() {
        return agencyWholesalePriceId;
    }

    public void setAgencyWholesalePriceId(Integer agencyWholesalePriceId) {
        this.agencyWholesalePriceId = agencyWholesalePriceId;
    }

    public BigDecimal getWholesalePrice() {
        return wholesalePrice;
    }

    public void setWholesalePrice(BigDecimal wholesalePrice) {
        this.wholesalePrice = wholesalePrice;
    }

    public Integer getMinimumQuantity() {
        return minimumQuantity;
    }

    public void setMinimumQuantity(Integer minimumQuantity) {
        this.minimumQuantity = minimumQuantity;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public AgencyWholesalePriceStatusEnum getStatus() {
        return status;
    }

    public void setStatus(AgencyWholesalePriceStatusEnum status) {
        this.status = status;
    }

    public Agency getAgency() {
        return agency;
    }

    public void setAgency(Agency agency) {
        this.agency = agency;
    }

    public VehicleTypeDetail getVehicleTypeDetail() {
        return vehicleTypeDetail;
    }

    public void setVehicleTypeDetail(VehicleTypeDetail vehicleTypeDetail) {
        this.vehicleTypeDetail = vehicleTypeDetail;
    }
}
