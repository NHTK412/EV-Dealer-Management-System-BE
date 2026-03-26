// package com.example.evsalesmanagement.dto.vehicledelivery;

// import java.time.LocalDate;
// import jakarta.validation.constraints.Future;
// import jakarta.validation.constraints.NotNull;
// import com.example.evsalesmanagement.enums.VehicleDeliveryStatusEnum;

// public class VehicleDeliveryRequestDTO {
    
//     private Integer orderId; 
//     private Integer employeeId;
    
//     @NotNull (message = "Ngày dự kiến giao xe không được để trống")
//     @Future (message = "Ngày dự kiến giao xe phải là một ngày trong tương lai")
//     private LocalDate expectedDeliveryDate;
    
//     private VehicleDeliveryStatusEnum status;

//     public Integer getOrderId() {
//         return orderId;
//     }
//     public void setOrderId(Integer orderId) {
//         this.orderId = orderId;
//     }
//     public Integer getEmployeeId() {
//         return employeeId;
//     }
//     public void setEmployeeId(Integer employeeId) {
//         this.employeeId = employeeId;
//     }
//     public LocalDate getExpectedDeliveryDate() {
//         return expectedDeliveryDate;
//     }
//     public void setExpectedDeliveryDate(LocalDate expectedDeliveryDate) {
//         this.expectedDeliveryDate = expectedDeliveryDate;
//     }
//     public VehicleDeliveryStatusEnum getStatus() {
//         return status;
//     }
//     public void setStatus(VehicleDeliveryStatusEnum status) {
//         this.status = status;
//     }
// }

package com.example.evsalesmanagement.dto.vehicledelivery;


public class VehicleDeliveryRequestDTO {

    private Integer employeeId;

    private String address;

    private String phoneNumber;

    private String name;

    private Integer orderId;

    

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    
    
    
}