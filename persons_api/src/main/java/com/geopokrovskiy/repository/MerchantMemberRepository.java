package com.geopokrovskiy.repository;

import com.geopokrovskiy.entity.user.MerchantMemberUserEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

import java.util.UUID;

public interface MerchantMemberRepository extends R2dbcRepository<MerchantMemberUserEntity, UUID> {
}
