package com.geopokrovskiy.repository;

import com.geopokrovskiy.entity.user.IndividualUserEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

import java.util.UUID;

public interface IndividualRepository extends R2dbcRepository<IndividualUserEntity, UUID> {
}
