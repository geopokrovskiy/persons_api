package com.geopokrovskiy.mapper.country;

import com.geopokrovskiy.dto.country.CountryResponseDto;
import com.geopokrovskiy.entity.country.CountryEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CountryMapper {
    @InheritInverseConfiguration
    CountryResponseDto map(CountryEntity countryEntity);
}


