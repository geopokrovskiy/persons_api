package com.geopokrovskiy.rest;

import com.geopokrovskiy.dto.user.IndividualRequestDto;
import com.geopokrovskiy.dto.user.IndividualResponseDto;
import com.geopokrovskiy.mapper.address.AddressMapper;
import com.geopokrovskiy.mapper.user.IndividualMapper;
import com.geopokrovskiy.service.IndividualService;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/user/individuals")
@Data
public class IndividualController {
    private final IndividualService individualService;
    private final IndividualMapper individualMapper;
    private final AddressMapper addressMapper;

    @PostMapping
    public ResponseEntity<Mono<IndividualResponseDto>> addNewIndividual(@RequestBody IndividualRequestDto individualRequestDto) {
        return new ResponseEntity<>(individualService.addNewIndividual(individualMapper.map(individualRequestDto),
                addressMapper.map(individualRequestDto.getAddress())).map(individualMapper::map), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mono<IndividualResponseDto>> getIndividualById(@PathVariable UUID id) {
        return new ResponseEntity<>(individualService.getIndividualById(id).map(individualMapper::map), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<Flux<IndividualResponseDto>> getAllIndividuals() {
        return new ResponseEntity<>(individualService.getAllIndividuals().map(individualMapper::map), HttpStatus.OK);
    }
}
