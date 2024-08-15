package com.geopokrovskiy.mapper.user;

import com.geopokrovskiy.dto.user.IndividualRequestDto;
import com.geopokrovskiy.dto.user.IndividualResponseDto;
import com.geopokrovskiy.entity.user.IndividualUserEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IndividualMapper {
    @InheritInverseConfiguration
    IndividualResponseDto map(IndividualUserEntity entity);

    IndividualUserEntity map(IndividualRequestDto requestDto);
}
