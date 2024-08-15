package com.geopokrovskiy.mapper.address;

import com.geopokrovskiy.dto.address.AddressRequestDto;
import com.geopokrovskiy.dto.address.AddressResponseDto;
import com.geopokrovskiy.entity.address.AddressEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    AddressEntity map(AddressRequestDto requestDto);
    @InheritInverseConfiguration
    AddressResponseDto map(AddressEntity addressEntity);
}
