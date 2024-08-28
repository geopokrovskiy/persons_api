package com.geopokrovskiy.dto.user;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.geopokrovskiy.dto.address.AddressResponseDto;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class MerchantMemberResponseDto {
    private UUID id;
    private UUID userId;
    private String memberRole;
    private String firstName;
    private String lastName;
    private LocalDateTime created;
    private LocalDateTime updated;
    private Boolean filled;
    private LocalDateTime archived;
    private LocalDateTime verified;
    private AddressResponseDto address;
}
