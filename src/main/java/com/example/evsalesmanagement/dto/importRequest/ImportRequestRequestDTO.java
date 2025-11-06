package com.example.evsalesmanagement.dto.importrequest;

import java.util.List;

import com.example.evsalesmanagement.dto.importrequestdetail.ImportRequestDetailRequestDTO;

public class ImportRequestRequestDTO {

    private String status;

    private String note;

    private Integer employeeId;

    private List<ImportRequestDetailRequestDTO> importRequestDetailRequests;

    public ImportRequestRequestDTO() {
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

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public List<ImportRequestDetailRequestDTO> getImportRequestDetails() {
        return importRequestDetailRequests;
    }

    public void setImportRequestDetails(List<ImportRequestDetailRequestDTO> importRequestDetailRequestsDTO) {
        this.importRequestDetailRequests = importRequestDetailRequestsDTO;
    }

}
