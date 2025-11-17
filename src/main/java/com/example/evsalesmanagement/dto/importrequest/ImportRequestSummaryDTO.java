package com.example.evsalesmanagement.dto.importrequest;

import com.example.evsalesmanagement.enums.ImportRequestStatusEnum;
import com.example.evsalesmanagement.model.ImportRequest;

public class ImportRequestSummaryDTO {

    private Integer importRequestId;

    // private EmployeeDTO employeeDTO;

    private Integer employeeId;

    private String employeeName;

    private String employeePosition;

    private Integer agencyId;

    private String agencyName;

    private ImportRequestStatusEnum status;

    private String note;

    public ImportRequestSummaryDTO(ImportRequest importRequest) {

        this.importRequestId = importRequest.getImportRequestId();

        this.status = importRequest.getStatus();

        this.note = importRequest.getNote();

        this.employeeId = importRequest.getEmployee().getEmployeeId();

        this.employeeName = importRequest.getEmployee().getEmployeeName();

        this.employeePosition = importRequest.getEmployee().getRole().getDisplayName();

        this.agencyId = importRequest.getEmployee().getAgency().getAgencyId();

        this.agencyName = importRequest.getEmployee().getAgency().getAgencyName();

    }

    public ImportRequestSummaryDTO() {
    }

    public Integer getImportRequestId() {
        return importRequestId;
    }

    public void setImportRequestId(Integer importRequestId) {
        this.importRequestId = importRequestId;
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

    public String getEmployeePosition() {
        return employeePosition;
    }

    public void setEmployeePosition(String employeePosition) {
        this.employeePosition = employeePosition;
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

    public ImportRequestStatusEnum getStatus() {
        return status;
    }

    public void setStatus(ImportRequestStatusEnum status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

}
