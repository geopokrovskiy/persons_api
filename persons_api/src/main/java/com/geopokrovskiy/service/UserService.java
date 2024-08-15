package com.geopokrovskiy.service;

import com.geopokrovskiy.entity.user.UserEntity;
import com.geopokrovskiy.repository.AddressRepository;
import com.geopokrovskiy.repository.UserRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@Data
@Slf4j
public class UserService {
    private final UserRepository userRepository;

    public Mono<UserEntity> addNewBaseUser(UserEntity userEntityMono) {
        return userRepository.save(userEntityMono);
    }

    public Mono<UserEntity> getBaseUserById(UUID id){
        return userRepository.findById(id);
    }


}
