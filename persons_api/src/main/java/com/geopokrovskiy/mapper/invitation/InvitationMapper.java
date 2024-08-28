package com.geopokrovskiy.mapper.invitation;

import com.geopokrovskiy.dto.invitation.InvitationCreateRequestDto;
import com.geopokrovskiy.dto.invitation.InvitationResponseDto;
import com.geopokrovskiy.entity.invitation.InvitationEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InvitationMapper {
    InvitationResponseDto map(InvitationEntity entity);

    InvitationEntity map(InvitationCreateRequestDto requestDto);
}
