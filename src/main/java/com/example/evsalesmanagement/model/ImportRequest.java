package com.example.evsalesmanagement.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

    // @OneToMany(mappedBy = "yeuCauNhapHang")
    // private List<ChiTietYeuCau> chiTietYeuCaus = new ArrayList<>();

    
    
}