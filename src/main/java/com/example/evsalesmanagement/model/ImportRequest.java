package com.example.evsalesmanagement.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

//YeuCauNhapHang = ImportRequest
@Entity
@Table(name = "ImportRequest")
public class ImportRequest extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ImportRequestId")
    private Integer importRequestId;

    @Column(name = "Status")
    private String status;

    @Column(name = "Note")
    private String note;

    @ManyToOne
    @JoinColumn(name = "EmployeeId")
    private Employee employee;

    @OneToMany(mappedBy = "importRequest", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ImportRequestDetail> importRequestDetails = new ArrayList<>();

    public Integer getImportRequestId() {
        return importRequestId;
    }

    public void setImportRequestId(Integer importRequestId) {
        this.importRequestId = importRequestId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public List<ImportRequestDetail> getImportRequestDetails() {
        return importRequestDetails;
    }

    public void setImportRequestDetails(List<ImportRequestDetail> importRequestDetails) {
        this.importRequestDetails = importRequestDetails;
    }

}