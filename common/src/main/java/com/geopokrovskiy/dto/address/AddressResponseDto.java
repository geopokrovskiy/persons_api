package com.geopokrovskiy.dto.address;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.geopokrovskiy.dto.country.CountryResponseDto;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AddressResponseDto {
    private String address;
    private String zipCode;
    private String city;
    private String state;
    private LocalDateTime created;
    private LocalDateTime updated;
    private LocalDateTime archived;
    private CountryResponseDto country;
}
