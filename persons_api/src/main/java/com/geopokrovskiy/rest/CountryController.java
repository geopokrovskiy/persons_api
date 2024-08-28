package com.geopokrovskiy.rest;

import com.geopokrovskiy.dto.country.CountryResponseDto;
import com.geopokrovskiy.mapper.country.CountryMapper;
import com.geopokrovskiy.service.CountryService;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@Data
@RequestMapping("/api/v1/countries")
public class CountryController {
    private final CountryService countryService;
    private final CountryMapper countryMapper;

    @GetMapping("/all")
    public ResponseEntity<Flux<CountryResponseDto>> getAllCountries() {
        return new ResponseEntity<>(countryService.getAllCountries().map(countryMapper::map), HttpStatus.OK);
    }

    @GetMapping("/alpha2")
    public ResponseEntity<Mono<CountryResponseDto>> getCountryByAlpha2(@RequestParam String alpha2) {
        return new ResponseEntity<>(countryService.getCountryByAlpha2(alpha2).map(countryMapper::map), HttpStatus.OK);
    }

    @GetMapping("/alpha3")
    public ResponseEntity<Mono<CountryResponseDto>> getCountryByAlpha3(@RequestParam String alpha3) {
        return new ResponseEntity<>(countryService.getCountryByAlpha3(alpha3).map(countryMapper::map), HttpStatus.OK);
    }
}
