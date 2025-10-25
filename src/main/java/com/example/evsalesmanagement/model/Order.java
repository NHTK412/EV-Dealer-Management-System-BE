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
@Table(name = "Order")
public class Order extends GhiNhanThoiGian {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OrderId")
    private Integer orderId;

    @Column(name = "ContractNumber")
    private String contractNumber;

    // @Column(name = "NgayTao")
    // private LocalDateTime ngayTao;

    @Column(name = "Status")
    private String status;

    @Column(name = "Note")
    private String note;

    @Column(name = "TotalOrderAmount")
    private BigDecimal totalOrderAmount;

    @ManyToOne
    @JoinColumn(name = "CustomerId")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "EmployeeId")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "AgencyId")
    private Agency agency;

    // @OneToOne(mappedBy = "donHang")
    // private BaoGia baoGia;

    // @OneToMany(mappedBy = "donHang")
    // private List<ThanhToan> thanhToans = new ArrayList<>();

    // @OneToOne(mappedBy = "donHang")
    // private GiaoXe giaoXe;

    // @OneToOne(mappedBy = "donHang")
    // private PhieuXuatKho phieuXuatKho;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    // public LocalDateTime getNgayTao() {
    //     return ngayTao;
    // }

    // public void setNgayTao(LocalDateTime ngayTao) {
    //     this.ngayTao = ngayTao;
    // }

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

    public BigDecimal getTotalOrderAmount() {
        return totalOrderAmount;
    }

    public void setTotalOrderAmount(BigDecimal totalOrderAmount) {
        this.totalOrderAmount = totalOrderAmount;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Agency getAgency() {
        return agency;
    }

    public void setAgency(Agency agency) {
        this.agency = agency;
    }

    // public BaoGia getBaoGia() {
    //     return baoGia;
    // }

    // public void setBaoGia(BaoGia baoGia) {
    //     this.baoGia = baoGia;
    // }

    // public List<ThanhToan> getThanhToans() {
    //     return thanhToans;
    // }

    // public void setThanhToans(List<ThanhToan> thanhToans) {
    //     this.thanhToans = thanhToans;
    // }

    // public GiaoXe getGiaoXe() {
    //     return giaoXe;
    // }

    // public void setGiaoXe(GiaoXe giaoXe) {
    //     this.giaoXe = giaoXe;
    // }

    // public PhieuXuatKho getPhieuXuatKho() {
    //     return phieuXuatKho;
    // }

    // public void setPhieuXuatKho(PhieuXuatKho phieuXuatKho) {
    //     this.phieuXuatKho = phieuXuatKho;
    // }
}
