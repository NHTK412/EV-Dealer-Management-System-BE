package com.example.evsalesmanagement.dto.importrequest;

import java.util.List;

import com.example.evsalesmanagement.dto.importrequestdetail.ImportRequestDetailRequestDTO;
import com.example.evsalesmanagement.enums.ImportRequestStatusEnum;

public class ImportRequestRequestDTO {

    private ImportRequestStatusEnum status;

    private String note;

    private Integer employeeId;

    private List<ImportRequestDetailRequestDTO> importRequestDetailRequests;

    public ImportRequestRequestDTO() {
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
