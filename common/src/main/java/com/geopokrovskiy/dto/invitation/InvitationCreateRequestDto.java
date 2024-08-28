package com.geopokrovskiy.dto.invitation;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.util.UUID;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class InvitationCreateRequestDto {

    private UUID merchantId;
    private String email;
    private String firstName;
    private String lastName;
}
