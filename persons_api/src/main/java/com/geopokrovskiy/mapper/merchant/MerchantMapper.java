package com.geopokrovskiy.mapper.merchant;


import com.geopokrovskiy.dto.merchant.MerchantCreateRequestDto;
import com.geopokrovskiy.dto.merchant.MerchantResponseDto;
import com.geopokrovskiy.entity.merchant.MerchantEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MerchantMapper {
    @InheritInverseConfiguration
    MerchantResponseDto map(MerchantEntity entity);

    MerchantEntity map(MerchantCreateRequestDto requestDto);
}
