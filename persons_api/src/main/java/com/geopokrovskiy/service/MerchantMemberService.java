package com.geopokrovskiy.service;

import com.geopokrovskiy.entity.address.AddressEntity;
import com.geopokrovskiy.entity.invitation.Status;
import com.geopokrovskiy.entity.user.MerchantMemberRole;
import com.geopokrovskiy.entity.user.MerchantMemberUserEntity;
import com.geopokrovskiy.entity.user.UserEntity;
import com.geopokrovskiy.repository.MerchantMemberRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.reactive.TransactionalOperator;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuples;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Data
@Slf4j
public class MerchantMemberService {
    private final MerchantMemberRepository merchantMemberRepository;
    private final UserService userService;
    private final InvitationService invitationService;
    private final AddressService addressService;
    private final CountryService countryService;
    private final TransactionalOperator transactionalOperator;

    public Mono<MerchantMemberUserEntity> addNewMerchantMember(UUID invitationId, AddressEntity address) {
        return invitationService.getInvitationById(invitationId).flatMap(retrievedInvitation -> {
            if (retrievedInvitation.getExpires().isBefore(LocalDateTime.now())) {
                LocalDateTime expirationDate = retrievedInvitation.getExpires();
                log.error("The invitation has expired at {}", expirationDate);
                return Mono.error(new IllegalArgumentException("Invitation expired at " + expirationDate));
            } else if (retrievedInvitation.getStatus() == Status.ACCEPTED) {
                log.error("The invitation has been already accepted");
                return Mono.error(new IllegalArgumentException("Invitation already accepted"));
            } else {
                return invitationService.updateInvitationStatus(Status.ACCEPTED, retrievedInvitation).flatMap(updatedInvitation -> {
                            return this.addNewMerchantMember(updatedInvitation.getFirstName(),
                                    updatedInvitation.getLastName(), retrievedInvitation.getMerchantId(),
                                    address);
                        })
                        .as(transactionalOperator::transactional);
            }
        }).switchIfEmpty(Mono.defer(() -> {
            log.error("The invitation with {} is not valid!", invitationId);
            return Mono.error(new IllegalArgumentException("The invitation is not valid"));
        }));
    }

    private Mono<MerchantMemberUserEntity> addNewMerchantMember(String firstName, String lastName, UUID merchantId, AddressEntity addressEntity) {
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
                            .status(com.geopokrovskiy.entity.status.Status.ACTIVE)
                            .filled(true)
                            .firstName(firstName)
                            .lastName(lastName)
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

                    MerchantMemberUserEntity newMerchantMember = new MerchantMemberUserEntity().toBuilder()
                            .userId(savedBaseUser.getId())
                            .merchantId(merchantId)
                            .memberRole(MerchantMemberRole.USER)
                            .build();

                    return merchantMemberRepository.save(newMerchantMember)
                            .map(savedMerchantMemberUser -> Tuples.of(savedAddress, savedBaseUser, newMerchantMember));
                })
                .map(tuple -> {
                    AddressEntity savedAddress = tuple.getT1();
                    UserEntity savedBaseUser = tuple.getT2();
                    MerchantMemberUserEntity savedMerchantMemberUser = tuple.getT3();


                    return savedMerchantMemberUser.toBuilder()
                            .firstName(firstName)
                            .lastName(lastName)
                            .address(savedAddress)
                            .addressId(savedAddress.getId())
                            .filled(true)
                            .created(savedBaseUser.getCreated())
                            .updated(savedBaseUser.getUpdated())
                            .build();
                }).doOnSuccess(individual -> {
                    log.info("A new merchant member user {} has been added!", individual);
                });
    }
}
