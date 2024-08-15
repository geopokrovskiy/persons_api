package com.geopokrovskiy.rest;

import com.geopokrovskiy.dto.country.CountryResponseDto;
import com.geopokrovskiy.mapper.country.CountryMapper;
import com.geopokrovskiy.service.CountryService;
import lombok.Data;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@Data
@RequestMapping("/api/v1/countries")
public class CountryController {
    private final CountryService countryService;
    private final CountryMapper countryMapper;

    @GetMapping("/all")
    public Flux<CountryResponseDto> getAllCountries() {
        return countryService.getAllCountries().map(countryMapper::map);
    }

    @GetMapping("/alpha2")
    public Mono<CountryResponseDto> getCountryByAlpha2(@RequestParam String alpha2) {
        return countryService.getCountryByAlpha2(alpha2).map(countryMapper::map);
    }

    @GetMapping("/alpha3")
    public Mono<CountryResponseDto> getCountryByAlpha3(@RequestParam String alpha3) {
        return countryService.getCountryByAlpha3(alpha3).map(countryMapper::map);
    }
}
