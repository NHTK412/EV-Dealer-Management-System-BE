package com.example.evsalesmanagement.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ImportRequestDetail")
public class ImportRequestDetail extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ImportRequestDetailId")
    private Integer importRequestDetailId;

    @Column(name = "Quantity")
    private Integer quantity; // quantity = Số lượng

    @ManyToOne
    @JoinColumn(name = "ImportRequestId")
    private ImportRequest importRequest;

    @ManyToOne
    @JoinColumn(name = "VehicleTypeDetailId")
    private VehicleTypeDetail vehicleTypeDetail;

    public Integer getImportRequestDetailId() {
        return importRequestDetailId;
    }

    public void setImportRequestDetailId(Integer importRequestDetailId) {
        this.importRequestDetailId = importRequestDetailId;
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

}