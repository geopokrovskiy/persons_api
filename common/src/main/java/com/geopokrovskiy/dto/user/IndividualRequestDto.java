package com.geopokrovskiy.dto.user;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.geopokrovskiy.dto.address.AddressRequestDto;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class IndividualRequestDto {
    private String passportNumber;
    private String phoneNumber;
    private String email;
    private String firstName;
    private String lastName;
    private AddressRequestDto address;
}
