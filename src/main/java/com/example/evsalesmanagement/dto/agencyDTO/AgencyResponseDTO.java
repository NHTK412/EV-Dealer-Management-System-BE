package com.example.evsalesmanagement.dto.agencyDTO;

public class AgencyResponseDTO {
    private Integer agencyId;
    private String agencyName;
    private String address;
    private String phoneNumber;
    private String email;
    private String status;

    public Integer getAgencyId() { return agencyId; }
    public void setAgencyId(Integer agencyId) { this.agencyId = agencyId; }
    public String getAgencyName() { return agencyName; }
    public void setAgencyName(String agencyName) { this.agencyName = agencyName; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
