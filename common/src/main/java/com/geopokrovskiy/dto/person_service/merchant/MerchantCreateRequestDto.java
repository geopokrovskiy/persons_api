package com.geopokrovskiy.dto.person_service.merchant;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class MerchantCreateRequestDto {

    private String companyName;
    private String companyId;
    private String email;
    private String phoneNumber;
}
