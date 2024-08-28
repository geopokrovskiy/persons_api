package com.geopokrovskiy.service;

import com.geopokrovskiy.entity.merchant.MerchantEntity;
import com.geopokrovskiy.entity.status.Status;
import com.geopokrovskiy.repository.MerchantRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Data
@Slf4j
public class MerchantService {
    private final MerchantRepository merchantRepository;

    public Mono<MerchantEntity> addNewMerchant(MerchantEntity merchantEntity) {
        return merchantRepository.save(new MerchantEntity().toBuilder().
                companyId(merchantEntity.getCompanyId()).
                companyName(merchantEntity.getCompanyName()).
                created(LocalDateTime.now()).
                updated(LocalDateTime.now()).
                phoneNumber(merchantEntity.getPhoneNumber()).
                email(merchantEntity.getEmail()).
                status(Status.ACTIVE).
                filled(true).
                build()).doOnSuccess(savedMerchant -> {
                    log.info("A new merchant {} has been added!", savedMerchant);
                }
        );
    }

    public Mono<MerchantEntity> getMerchantById(UUID id) {
        return merchantRepository.findById(id);
    }
}
