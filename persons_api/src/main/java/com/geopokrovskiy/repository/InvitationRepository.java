package com.geopokrovskiy.repository;

import com.geopokrovskiy.entity.invitation.InvitationEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

import java.util.UUID;

public interface InvitationRepository extends R2dbcRepository<InvitationEntity, UUID> {
}
