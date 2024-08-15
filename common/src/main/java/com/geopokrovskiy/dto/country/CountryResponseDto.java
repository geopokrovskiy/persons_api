package com.geopokrovskiy.dto.country;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CountryResponseDto {
    private int id;
    private LocalDateTime created;
    private LocalDateTime updated;
    private String name;
    private String alpha2;
    private String alpha3;
}
