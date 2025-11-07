package com.example.evsalesmanagement.dto.importrequest;

import java.util.ArrayList;
import java.util.List;

import com.example.evsalesmanagement.dto.importrequestdetail.ImportRequestDetailResponseDTO;
import com.example.evsalesmanagement.model.ImportRequest;

public class ImportRequestResponseDTO {
    private Integer importRequestId;

    private String status;

    private String note;

    // private EmployeeDTO employeeDTO;

    private Integer employeeId;

    private String employeeName;

    private String employeePhoneNumber;

    private String employeeEmail;

    private String employeePosition;

    private Integer agencyId;

    private String agencyName;

    private String agencyAddress;

    private List<ImportRequestDetailResponseDTO> importRequestDetails;

    public ImportRequestResponseDTO(ImportRequest importRequest) {
        this.importRequestId = importRequest.getImportRequestId();
        this.status = importRequest.getStatus().getDisplayName();
        this.note = importRequest.getNote();
        // this.employeeDTO = new EmployeeDTO(importRequest.getEmployee());

        this.employeeId = importRequest.getEmployee().getEmployeeId();

        this.employeeName = importRequest.getEmployee().getEmployeeName();

        this.employeePhoneNumber = importRequest.getEmployee().getPhoneNumber();

        this.employeeEmail = importRequest.getEmployee().getEmail();

        this.employeePosition = importRequest.getEmployee().getPosition().getDisplayName();

        this.agencyId = importRequest.getEmployee().getAgency().getAgencyId();

        this.agencyName = importRequest.getEmployee().getAgency().getAgencyName();

        this.agencyAddress = importRequest.getEmployee().getAgency().getAddress();

        this.importRequestDetails = new ArrayList<>();

    }

    public ImportRequestResponseDTO() {
    }

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

    // public EmployeeDTO getEmployeeDTO() {
    // return employeeDTO;
    // }

    // public void setEmployeeDTO(EmployeeDTO employeeDTO) {
    // this.employeeDTO = employeeDTO;
    // }

    public List<ImportRequestDetailResponseDTO> getImportRequestDetails() {
        return importRequestDetails;
    }

    public void setImportRequestDetails(List<ImportRequestDetailResponseDTO> importRequestDetails) {
        this.importRequestDetails = importRequestDetails;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeePhoneNumber() {
        return employeePhoneNumber;
    }

    public void setEmployeePhoneNumber(String employeePhoneNumber) {
        this.employeePhoneNumber = employeePhoneNumber;
    }

    public Integer getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(Integer agencyId) {
        this.agencyId = agencyId;
    }

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public String getEmployeePosition() {
        return employeePosition;
    }

    public void setEmployeePosition(String employeePosition) {
        this.employeePosition = employeePosition;
    }

    public String getAgencyAddress() {
        return agencyAddress;
    }

    public void setAgencyAddress(String agencyAddress) {
        this.agencyAddress = agencyAddress;
    }

}
