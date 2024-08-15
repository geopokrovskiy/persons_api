package com.geopokrovskiy.repository;

import com.geopokrovskiy.entity.country.CountryEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface CountryRepository extends R2dbcRepository<CountryEntity, Integer> {
    Mono<CountryEntity> getCountryEntityByAlpha2(String alpha2);

    Mono<CountryEntity> getCountryEntityByAlpha3(String alpha3);

    Mono<CountryEntity> getCountryEntityById(Integer id);

}
