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
public class CreateVehicleTypeDTO {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreateVehicleTypeDetailDTO {
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

    private List<CreateVehicleTypeDetailDTO> vehicleTypeDetails;
    private List<Integer> vehicleCategoryIds;

}
