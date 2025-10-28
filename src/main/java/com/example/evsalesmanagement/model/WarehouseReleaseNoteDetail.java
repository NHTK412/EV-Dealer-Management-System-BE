package com.example.evsalesmanagement.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

//ChiTietPhieuXuat = WarehouseReleaseNoteDetail
@Entity
@Table(name = "WarehouseReleaseNoteDetail")
@IdClass(WarehouseReleaseNoteDetail.WarehouseReleaseNoteDetailId.class)
public class WarehouseReleaseNoteDetail extends Base{
    @Id
    @Column(name = "WarehouseReleaseNoteId")
    private Integer warehouseReleaseNoteId;

    @Id
    @Column(name = "vehicleId")
    private Integer vehicleId;

    @ManyToOne
    @JoinColumn(name = "WarehouseReleaseNoteId", insertable = false, updatable = false)
    private WarehouseReleaseNote warehouseReleaseNote;

    @ManyToOne
    @JoinColumn(name = "vehicleId", insertable = false, updatable = false)
    private Vehicle vehicle;


    public Integer getWarehouseReleaseNoteId() {
        return warehouseReleaseNoteId;
    }



    public void setWarehouseReleaseNoteId(Integer warehouseReleaseNoteId) {
        this.warehouseReleaseNoteId = warehouseReleaseNoteId;
    }



    public Integer getVehicleId() {
        return vehicleId;
    }



    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }



    public WarehouseReleaseNote getWarehouseReleaseNote() {
        return warehouseReleaseNote;
    }



    public void setWarehouseReleaseNote(WarehouseReleaseNote warehouseReleaseNote) {
        this.warehouseReleaseNote = warehouseReleaseNote;
    }



    public Vehicle getVehicle() {
        return vehicle;
    }



    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }



    @Embeddable
    public static class WarehouseReleaseNoteDetailId {
        private Integer warehouseReleaseNoteId;
        private Integer vehicleId;

        public WarehouseReleaseNoteDetailId() {
        }

        public Integer getWarehouseReleaseNoteId() {
            return warehouseReleaseNoteId;
        }

        public void setWarehouseReleaseNoteId(Integer warehouseReleaseNoteId) {
            this.warehouseReleaseNoteId = warehouseReleaseNoteId;
        }

        public Integer getVehicleId() {
            return vehicleId;
        }
        
        public void setVehicleId(Integer vehicleId) {
            this.vehicleId = vehicleId;
        }
        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;
            WarehouseReleaseNoteDetailId that = (WarehouseReleaseNoteDetailId) o;
            return warehouseReleaseNoteId.equals(that.warehouseReleaseNoteId) && vehicleId.equals(that.vehicleId);
        }

        @Override
        public int hashCode() {
            return java.util.Objects.hash(warehouseReleaseNoteId, vehicleId);
        }
    }
}