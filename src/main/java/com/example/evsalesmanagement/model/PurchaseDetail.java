package com.example.evsalesmanagement.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "PurchaseDetail")
@IdClass(PurchaseDetail.PurchaseDetailId.class)
public class PurchaseDetail extends GhiNhanThoiGian {
    @Id
    @Column(name = "VehicleId")
    private Integer vehicleId;

    @Id
    @Column(name = "PurchaseOrderId")
    private Integer purchaseOrderId;

    @ManyToOne
    @JoinColumn(name = "VehicleId", insertable = false, updatable = false)
    private Vehicle vehicle;

    @ManyToOne
    @JoinColumn(name = "PurchaseOrderId", insertable = false, updatable = false)
    private PurchaseOrder purchaseOrder;

    public Integer getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Integer getPurchaseOrderId() {
        return purchaseOrderId;
    }

    public void setPurchaseOrderId(Integer purchaseOrderId) {
        this.purchaseOrderId = purchaseOrderId;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public PurchaseOrder getPurchaseOrder() {
        return purchaseOrder;
    }

    public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }

    @Embeddable
    public static class PurchaseDetailId {
        private Integer vehicleId;
        private Integer purchaseOrderId;

        public PurchaseDetailId() {
        }

        public PurchaseDetailId(Integer vehicleId, Integer purchaseOrderId) {
            this.vehicleId = vehicleId;
            this.purchaseOrderId = purchaseOrderId;
        }

        public Integer getVehicleId() {
            return vehicleId;
        }

        public void setVehicleId(Integer vehicleId) {
            this.vehicleId = vehicleId;
        }

        public Integer getPurchaseOrderId() {
            return purchaseOrderId;
        }

        public void setPurchaseOrderId(Integer purchaseOrderId) {
            this.purchaseOrderId = purchaseOrderId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;
            PurchaseDetailId that = (PurchaseDetailId) o;
            return vehicleId.equals(that.vehicleId) && purchaseOrderId.equals(that.purchaseOrderId);
        }

        @Override
        public int hashCode() {
            return java.util.Objects.hash(vehicleId, purchaseOrderId);
        }
    }
}
