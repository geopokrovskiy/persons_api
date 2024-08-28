package com.geopokrovskiy.service;

import com.geopokrovskiy.entity.invitation.InvitationEntity;
import com.geopokrovskiy.entity.invitation.Status;
import com.geopokrovskiy.repository.InvitationRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Service
@Slf4j
public class InvitationService {
    private final InvitationRepository invitationRepository;
    private final MerchantService merchantService;

    private Integer expirationTime = 1;

    public Mono<InvitationEntity> addNewInvitation(InvitationEntity invitation) {
        return merchantService.getMerchantById(invitation.getMerchantId())
                .flatMap(retrievedMerchant -> {
                    if (retrievedMerchant.getStatus() == com.geopokrovskiy.entity.status.Status.DELETED) {
                        log.error("Merchant with id {} has been deleted", invitation.getMerchantId());
                        return Mono.error(new IllegalArgumentException("Merchant has been deleted"));
                    }

                    log.info("Merchant with id {} is found", invitation.getMerchantId());
                    return invitationRepository.save(new InvitationEntity().toBuilder()
                            .firstName(invitation.getFirstName())
                            .lastName(invitation.getLastName())
                            .created(LocalDateTime.now())
                            .expires(LocalDateTime.now().plusDays(expirationTime))
                            .status(Status.VALID)
                            .email(invitation.getEmail())
                            .merchantId(invitation.getMerchantId())
                            .build());
                })
                .switchIfEmpty(Mono.defer(() -> {
                    UUID id = invitation.getMerchantId();
                    log.error("Merchant with id {} is not found", id);
                    return Mono.error(new IllegalArgumentException("Merchant with given id " + id + " not found"));

                }));
    }

    public Mono<InvitationEntity> getInvitationById(UUID id) {
        return invitationRepository.findById(id);
    }

    public Mono<InvitationEntity> updateInvitationStatus(Status status, InvitationEntity invitation) {
        log.info("The invitation with id {} ha been updated with status {} ", invitation.getId(), status);
        invitation.setStatus(status);
        return invitationRepository.save(invitation);
    }
}
