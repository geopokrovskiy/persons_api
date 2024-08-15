package com.geopokrovskiy.repository;

import com.geopokrovskiy.entity.user.UserEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

import java.util.UUID;

public interface UserRepository extends R2dbcRepository<UserEntity, UUID> {
}
