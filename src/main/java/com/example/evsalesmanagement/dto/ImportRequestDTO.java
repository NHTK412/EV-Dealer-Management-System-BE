package com.example.evsalesmanagement.dto;

import java.util.ArrayList;
import java.util.List;

import com.example.evsalesmanagement.model.ImportRequest;

public class ImportRequestDTO {
    private Integer importRequestId;

    private String status;

    private String note;

    private EmployeeDTO employeeDTO;

    private List<ImportRequestDetailDTO> importRequestDetails;

    public ImportRequestDTO(ImportRequest importRequest) {
        this.importRequestId = importRequest.getImportRequestId();
        this.status = importRequest.getStatus();
        this.note = importRequest.getNote();
        this.employeeDTO = new EmployeeDTO(importRequest.getEmployee());
        this.importRequestDetails = new ArrayList<>();
      
    }
        

    public ImportRequestDTO() {
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


    public EmployeeDTO getEmployeeDTO() {
        return employeeDTO;
    }


    public void setEmployeeDTO(EmployeeDTO employeeDTO) {
        this.employeeDTO = employeeDTO;
    }


    public List<ImportRequestDetailDTO> getImportRequestDetails() {
        return importRequestDetails;
    }


    public void setImportRequestDetails(List<ImportRequestDetailDTO> importRequestDetails) {
        this.importRequestDetails = importRequestDetails;
    }

    

}
