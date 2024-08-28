package com.geopokrovskiy.mapper.user;

import com.geopokrovskiy.dto.user.MerchantMemberCreateRequestDto;
import com.geopokrovskiy.dto.user.MerchantMemberResponseDto;
import com.geopokrovskiy.entity.user.MerchantMemberUserEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MerchantMemberMapper {
    @InheritInverseConfiguration
    MerchantMemberResponseDto map(MerchantMemberUserEntity entity);

    MerchantMemberUserEntity map(MerchantMemberCreateRequestDto requestDto);
}
