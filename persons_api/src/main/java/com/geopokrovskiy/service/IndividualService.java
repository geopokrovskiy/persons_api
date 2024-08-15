package com.geopokrovskiy.service;

import com.geopokrovskiy.entity.address.AddressEntity;
import com.geopokrovskiy.entity.status.Status;
import com.geopokrovskiy.entity.user.IndividualUserEntity;
import com.geopokrovskiy.entity.user.UserEntity;
import com.geopokrovskiy.repository.IndividualRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.reactive.TransactionalOperator;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuples;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Service
@Slf4j
public class IndividualService {
    private final IndividualRepository individualRepository;
    private final UserService userService;
    private final AddressService addressService;
    private final CountryService countryService;
    private final TransactionalOperator transactionalOperator;

    public Mono<IndividualUserEntity> addNewIndividual(IndividualUserEntity individualUser, AddressEntity addressEntity) {
        return countryService.getCountryByAlpha3(addressEntity.getAlpha3())
                .flatMap(retrievedCountry -> {
                    AddressEntity newAddress = new AddressEntity().toBuilder()
                            .state(addressEntity.getState())
                            .city(addressEntity.getCity())
                            .zipCode(addressEntity.getZipCode())
                            .address(addressEntity.getAddress())
                            .countryId(retrievedCountry.getId())
                            .country(retrievedCountry)
                            .created(LocalDateTime.now())
                            .updated(LocalDateTime.now())
                            .build();
                    return addressService.addNewAddress(newAddress);
                })
                .flatMap(savedAddress -> {
                    UserEntity newUser = new UserEntity().toBuilder()
                            .created(LocalDateTime.now())
                            .updated(LocalDateTime.now())
                            .status(Status.ACTIVE)
                            .filled(true)
                            .firstName(individualUser.getFirstName())
                            .lastName(individualUser.getLastName())
                            .addressId(savedAddress.getId())
                            .address(savedAddress)
                            .secretKey("MOCK_SECRET_KEY")
                            .build();
                    return userService.addNewBaseUser(newUser)
                            .map(savedBaseUser -> Tuples.of(savedAddress, savedBaseUser));
                })
                .flatMap(tuple -> {
                    AddressEntity savedAddress = tuple.getT1();
                    UserEntity savedBaseUser = tuple.getT2();

                    IndividualUserEntity newIndividual = new IndividualUserEntity().toBuilder()
                            .passportNumber(individualUser.getPassportNumber())
                            .userId(savedBaseUser.getId())
                            .email(individualUser.getEmail())
                            .phoneNumber(individualUser.getPhoneNumber())
                            .build();

                    return individualRepository.save(newIndividual)
                            .map(savedIndividualUser -> Tuples.of(savedAddress, savedBaseUser, savedIndividualUser));
                })
                .map(tuple -> {
                    AddressEntity savedAddress = tuple.getT1();
                    UserEntity savedBaseUser = tuple.getT2();
                    IndividualUserEntity savedIndividualUser = tuple.getT3();


                    return savedIndividualUser.toBuilder()
                            .firstName(individualUser.getFirstName())
                            .lastName(individualUser.getLastName())
                            .address(savedAddress)
                            .addressId(savedAddress.getId())
                            .filled(true)
                            .created(savedBaseUser.getCreated())
                            .updated(savedBaseUser.getUpdated())
                            .build();
                }).doOnSuccess(individual -> {
                    log.info("A new user {} has been added!", individual);
                })
                .as(transactionalOperator::transactional);
    }

    public Mono<IndividualUserEntity> getIndividualById(UUID id) {
        return individualRepository.findById(id)
                .flatMap(retrievedIndividual ->
                        userService.getBaseUserById(retrievedIndividual.getUserId())
                                .flatMap(retrievedBaseUser -> addressService.getAddressById(retrievedBaseUser.getAddressId()).
                                        map(retrievedAddress -> retrievedIndividual.toBuilder()
                                                .lastName(retrievedBaseUser.getLastName())
                                                .firstName(retrievedBaseUser.getFirstName())
                                                .created(retrievedBaseUser.getCreated())
                                                .updated(retrievedBaseUser.getUpdated())
                                                .address(retrievedAddress)
                                                .addressId(retrievedAddress.getId())
                                                .filled(retrievedBaseUser.isFilled())
                                                .verified(retrievedBaseUser.getVerifiedAt())
                                                .archived(retrievedBaseUser.getArchivedAt())
                                                .build())
                                )
                );
    }

    public Flux<IndividualUserEntity> getAllIndividuals() {
        return individualRepository.findAll().flatMap(individual -> this.getIndividualById(individual.getId()));
    }


}





