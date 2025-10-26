package com.example.evsalesmanagement.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

//ChiTietPhieuNhap = WarehouseReceiptDetail
@Entity
@Table(name = "WarehouseReceiptDetail")
@IdClass(WarehouseReceiptDetail.WarehouseReceiptDetailId.class)
public class WarehouseReceiptDetail extends TimeStampRecord {
    @Id
    @Column(name = "VehicleId")
    private Integer vehicleId;

    @Id
    @Column(name = "WarehouseReceiptId")
    private Integer warehouseReceiptId;

    @ManyToOne
    @JoinColumn(name = "VehicleId", insertable = false, updatable = false)
    private Vehicle vehicle;

    @ManyToOne
    @JoinColumn(name = "WarehouseReceiptId", insertable = false, updatable = false)
    private WarehouseReceipt warehouseReceipt;


    public Integer getVehicleId() {
        return vehicleId;
    }




    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }




    public Integer getWarehouseReceiptId() {
        return warehouseReceiptId;
    }




    public void setWarehouseReceiptId(Integer warehouseReceiptId) {
        this.warehouseReceiptId = warehouseReceiptId;
    }




    public Vehicle getVehicle() {
        return vehicle;
    }




    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }




    public WarehouseReceipt getWarehouseReceipt() {
        return warehouseReceipt;
    }




    public void setWarehouseReceipt(WarehouseReceipt warehouseReceipt) {
        this.warehouseReceipt = warehouseReceipt;
    }




    @Embeddable
    public static class WarehouseReceiptDetailId {
        private Integer vehicleId;
        private Integer warehouseReceiptId;

        public WarehouseReceiptDetailId() {
        }

        public WarehouseReceiptDetailId(Integer vehicleId, Integer warehouseReceiptId) {
            this.vehicleId = vehicleId;
            this.warehouseReceiptId = warehouseReceiptId;
        }

        public Integer getVehicleId() {
            return vehicleId;
        }

        public void setVehicleId(Integer vehicleId) {
            this.vehicleId = vehicleId;
        }

        public Integer getWarehouseReceiptId() {
            return warehouseReceiptId;
        }

         public void setPurchaseOrderId(Integer warehouseReceiptId) {
        this.warehouseReceiptId = warehouseReceiptId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;
            WarehouseReceiptDetailId that = (WarehouseReceiptDetailId) o;
            return vehicleId.equals(that.vehicleId) && warehouseReceiptId.equals(that.warehouseReceiptId);
        }

        @Override
        public int hashCode() {
            return java.util.Objects.hash(vehicleId, warehouseReceiptId);
        }
    }

   
}