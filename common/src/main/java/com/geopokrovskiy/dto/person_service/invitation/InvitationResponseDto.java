package com.geopokrovskiy.dto.person_service.invitation;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class InvitationResponseDto {
    private UUID id;
    private UUID merchantId;
    private String email;
    private String firstName;
    private String lastName;
    private LocalDateTime created;
    private LocalDateTime expires;
}
