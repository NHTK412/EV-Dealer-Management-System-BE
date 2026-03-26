package com.example.evsalesmanagement.dto.vehicletype;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class VehicleTypeResponseDTO_v2 {

    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class VehicleTypeDetailResponseDTO {
        private Integer vehicleTypeDetailId;
        private String vehicleImage;
        private String configuration;
        private String color;
        private String version;
        private String features;
        private BigDecimal price;
    }

    private Integer vehicleTypeId;
    private String vehicleTypeName;
    private Integer manufactureYear;
    private String description;
    private java.util.List<VehicleTypeDetailResponseDTO> vehicleTypeDetails;

}
