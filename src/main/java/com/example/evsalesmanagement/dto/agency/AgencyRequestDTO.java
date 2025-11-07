package com.example.evsalesmanagement.dto.agency;

import com.example.evsalesmanagement.enums.AgencyStatusEnum;


public class AgencyRequestDTO {

    private String agencyName;

    private String address;

    private String phoneNumber;

    private String email;

    
    private AgencyStatusEnum status;

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public AgencyStatusEnum getStatus() {
        return status;
    }

    public void setStatus(AgencyStatusEnum status) {
        this.status = status;
    }

}
