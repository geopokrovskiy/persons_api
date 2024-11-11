package com.geopokrovskiy.dto.person_service.user;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.geopokrovskiy.dto.person_service.address.AddressRequestDto;
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
