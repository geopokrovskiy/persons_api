package com.geopokrovskiy.service;

import com.geopokrovskiy.entity.address.AddressEntity;
import com.geopokrovskiy.repository.AddressRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@Data
@Slf4j
public class AddressService {
    private final AddressRepository addressRepository;
    private final CountryService countryService;

    public final Mono<AddressEntity> addNewAddress(AddressEntity address) {
        return this.addressRepository.save(address).doOnSuccess(addressEntity -> {
            log.info("A new address {} has been added!", addressEntity);
        });
    }

    public final Mono<AddressEntity> getAddressById(UUID id) {
        return addressRepository.findById(id).flatMap(retrievedAddress -> countryService.getCountryById(retrievedAddress.getCountryId())
                .map(retrievedCountry -> retrievedAddress.toBuilder()
                        .city(retrievedAddress.getCity())
                        .address(retrievedAddress.getAddress())
                        .zipCode(retrievedAddress.getZipCode())
                        .updated(retrievedAddress.getUpdated())
                        .created(retrievedAddress.getCreated())
                        .country(retrievedCountry)
                        .countryId(retrievedCountry.getId())
                        .state(retrievedAddress.getState())
                        .build()));
    }
}
