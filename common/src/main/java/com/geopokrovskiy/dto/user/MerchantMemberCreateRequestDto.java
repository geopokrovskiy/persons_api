package com.geopokrovskiy.dto.user;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.geopokrovskiy.dto.address.AddressRequestDto;
import lombok.Data;

import java.util.UUID;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class MerchantMemberCreateRequestDto {
    private UUID invitationId;
    private AddressRequestDto address;
}
