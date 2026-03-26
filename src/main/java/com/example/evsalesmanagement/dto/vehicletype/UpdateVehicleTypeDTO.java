package com.example.evsalesmanagement.dto.vehicletype;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateVehicleTypeDTO {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UpdateVehicleTypeDetailDTO {
        private Integer vehicleTypeDetailId;
        private String vehicleImage;
        private String configuration;
        private String color;
        private String version;
        private String features;
        private BigDecimal price;
    }

    private String vehicleTypeName;
    private Integer manufactureYear;
    private String description;

    private List<UpdateVehicleTypeDetailDTO> vehicleTypeDetails;

    private List<Integer> categoryList;

}
