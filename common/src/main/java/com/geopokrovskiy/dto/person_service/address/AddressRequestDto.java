package com.geopokrovskiy.dto.person_service.address;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;


@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AddressRequestDto {
    private String address;
    private String zipCode;
    private String city;
    private String state;
    private String alpha3;

}
