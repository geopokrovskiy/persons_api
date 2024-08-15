package com.geopokrovskiy.repository;

import com.geopokrovskiy.entity.address.AddressEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

import java.util.UUID;

public interface AddressRepository extends R2dbcRepository<AddressEntity, UUID> {
}
