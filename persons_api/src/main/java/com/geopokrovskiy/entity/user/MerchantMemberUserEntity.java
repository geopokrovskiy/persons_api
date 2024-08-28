package com.geopokrovskiy.entity.user;

import com.geopokrovskiy.entity.address.AddressEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Table("person.merchant_members")
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class MerchantMemberUserEntity {

    @Id
    private UUID id;
    @Column
    private UUID userId;


    @Column
    private UUID merchantId;
    @Column
    private MerchantMemberRole memberRole;
    @Transient
    private AddressEntity address;

    private String firstName;
    private String lastName;
    private UUID addressId;
    private LocalDateTime created;
    private LocalDateTime updated;
    private LocalDateTime archived;
    private LocalDateTime verified;
    private Boolean filled;

}
