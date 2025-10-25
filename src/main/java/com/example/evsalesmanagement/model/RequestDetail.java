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
@Table(name = "RequestDetail")
@IdClass(RequestDetail.RequestDetailId.class)
public class RequestDetail extends GhiNhanThoiGian {
    @Id
    @Column(name = "RequestId")
    private Integer requestId;

    @Id
    @Column(name = "VehicleDetailId")
    private Integer vehicleDetailId;

    @Column(name = "Quantity")
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "RequestId", insertable = false, updatable = false)
    private PurchaseRequest purchaseRequest;

    @ManyToOne
    @JoinColumn(name = "VehicleDetailId", insertable = false, updatable = false)
    private VehicleDetail vehicleDetail;

    public Integer getRequestId() {
        return requestId;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    public Integer getVehicleDetailId() {
        return vehicleDetailId;
    }

    public void setVehicleDetailId(Integer vehicleDetailId) {
        this.vehicleDetailId = vehicleDetailId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public PurchaseRequest getPurchaseRequest() {
        return purchaseRequest;
    }

    public void setPurchaseRequest(PurchaseRequest purchaseRequest) {
        this.purchaseRequest = purchaseRequest;
    }

    public VehicleDetail getVehicleDetail() {
        return vehicleDetail;
    }

    public void setVehicleDetail(VehicleDetail vehicleDetail) {
        this.vehicleDetail = vehicleDetail;
    }

    @Embeddable
    public static class RequestDetailId {
        private Integer requestId;
        private Integer vehicleDetailId;

        public RequestDetailId() {
        }

        public RequestDetailId(Integer requestId, Integer vehicleDetailId) {
            this.requestId = requestId;
            this.vehicleDetailId = vehicleDetailId;
        }

        public Integer getRequestId() {
            return requestId;
        }

        public void setRequestId(Integer requestId) {
            this.requestId = requestId;
        }

        public Integer getVehicleDetailId() {
            return vehicleDetailId;
        }

        public void setVehicleDetailId(Integer vehicleDetailId) {
            this.vehicleDetailId = vehicleDetailId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;
            RequestDetailId that = (RequestDetailId) o;
            return requestId.equals(that.requestId) && vehicleDetailId.equals(that.vehicleDetailId);
        }

        @Override
        public int hashCode() {
            return java.util.Objects.hash(requestId, vehicleDetailId);
        }
    }

}
