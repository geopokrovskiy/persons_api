package com.geopokrovskiy.rest;

import com.geopokrovskiy.dto.user.IndividualRequestDto;
import com.geopokrovskiy.dto.user.IndividualResponseDto;
import com.geopokrovskiy.mapper.address.AddressMapper;
import com.geopokrovskiy.mapper.user.IndividualMapper;
import com.geopokrovskiy.service.IndividualService;
import lombok.Data;
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
    public Mono<IndividualResponseDto> addNewIndividual(@RequestBody IndividualRequestDto individualRequestDto) {
        return individualService.addNewIndividual(individualMapper.map(individualRequestDto),
                addressMapper.map(individualRequestDto.getAddress())).map(individualMapper::map);
    }

    @GetMapping("/{id}")
    public Mono<IndividualResponseDto> getIndividualById(@PathVariable UUID id) {
        return individualService.getIndividualById(id).map(individualMapper::map);
    }

    @GetMapping("/all")
    public Flux<IndividualResponseDto> getAllIndividuals() {
        return individualService.getAllIndividuals().map(individualMapper::map);
    }
}
