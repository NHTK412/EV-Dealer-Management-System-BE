package com.example.evsalesmanagement.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

//ChiTietYeuCau = ImportRequestDetail
@Entity
@Table(name = "ImportRequestDetail")
@IdClass(ImportRequestDetail.ImportRequestDetailId.class)
public class ImportRequestDetail extends Base {
    @Id
    @Column(name = "ImportRequestId")
    private Integer importRequestId;

    @Id
    @Column(name = "VehicleTypeDetailId")
    private Integer vehicleTypeDetailId;

    @Column(name = "Quantity")
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "ImportRequestId", insertable = false, updatable = false)
    private ImportRequest importRequest;

    @ManyToOne
    @JoinColumn(name = "VehicleTypeDetailId", insertable = false, updatable = false)
    private VehicleTypeDetail vehicleTypeDetail;

    public Integer getImportRequestId() {
        return importRequestId;
    }

    public void setImportRequestId(Integer importRequestId) {
        this.importRequestId = importRequestId;
    }

    public Integer getVehicleTypeDetailId() {
        return vehicleTypeDetailId;
    }

    public void setVehicleTypeDetailId(Integer vehicleTypeDetailId) {
        this.vehicleTypeDetailId = vehicleTypeDetailId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public ImportRequest getImportRequest() {
        return importRequest;
    }

    public void setImportRequest(ImportRequest importRequest) {
        this.importRequest = importRequest;
    }

    public VehicleTypeDetail getVehicleTypeDetail() {
        return vehicleTypeDetail;
    }

    public void setVehicleTypeDetail(VehicleTypeDetail vehicleTypeDetail) {
        this.vehicleTypeDetail = vehicleTypeDetail;
    }

    @Embeddable
    public static class ImportRequestDetailId {
        private Integer importRequestId;
        private Integer vehicleTypeDetailId;

        public ImportRequestDetailId() {
        }

        public ImportRequestDetailId(Integer importRequestId, Integer vehicleTypeDetailId) {
            this.importRequestId = importRequestId;
            this.vehicleTypeDetailId = vehicleTypeDetailId;
        }

        public Integer getImportRequestId() {
            return importRequestId;
        }

        public void setImportRequestId(Integer importRequestId) {
            this.importRequestId = importRequestId;
        }

        public Integer getVehicleTypeDetailId() {
            return vehicleTypeDetailId;
        }

        public void setVehicleTypeDetailId(Integer vehicleTypeDetailId) {
            this.vehicleTypeDetailId = vehicleTypeDetailId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;
            ImportRequestDetailId that = (ImportRequestDetailId) o;
            return importRequestId.equals(that.importRequestId) && vehicleTypeDetailId.equals(that.vehicleTypeDetailId);
        }

        @Override
        public int hashCode() {
            return java.util.Objects.hash(importRequestId, vehicleTypeDetailId);
        }
    }

}