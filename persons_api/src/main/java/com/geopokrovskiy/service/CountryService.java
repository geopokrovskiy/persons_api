package com.geopokrovskiy.service;

import com.geopokrovskiy.entity.country.CountryEntity;
import com.geopokrovskiy.repository.CountryRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Data
@Slf4j
public class CountryService {
    private final CountryRepository countryRepository;

    public Flux<CountryEntity> getAllCountries() {
        return countryRepository.findAll();
    }

    public Mono<CountryEntity> getCountryByAlpha2(String alpha2) {
        return countryRepository.getCountryEntityByAlpha2(alpha2);
    }

    public Mono<CountryEntity> getCountryByAlpha3(String alpha3) {
        return countryRepository.getCountryEntityByAlpha3(alpha3);
    }

    public Mono<CountryEntity> getCountryById(Integer id) {
        return countryRepository.getCountryEntityById(id);
    }

}
