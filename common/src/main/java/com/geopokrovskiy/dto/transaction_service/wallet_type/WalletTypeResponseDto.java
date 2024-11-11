package com.geopokrovskiy.dto.transaction_service.wallet_type;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.geopokrovskiy.dto.transaction_service.wallet.WalletResponseDto;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class WalletTypeResponseDto {
    private UUID uid;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

    private String name;

    private String currency_code;

    private String status;

    private LocalDateTime archivedAt;

    private String userType;

    private String creator;

    private String modifier;

}
