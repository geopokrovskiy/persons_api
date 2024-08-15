package com.geopokrovskiy.entity.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Table("person.merchant_members")
@Data
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
    private String memberRole;

}
