package com.geopokrovskiy.repository;

import com.geopokrovskiy.entity.merchant.MerchantEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

import java.util.UUID;

public interface MerchantRepository extends R2dbcRepository<MerchantEntity, UUID> {


}
