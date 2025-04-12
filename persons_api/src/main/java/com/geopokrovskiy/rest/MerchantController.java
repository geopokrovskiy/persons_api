package com.geopokrovskiy.rest;

import com.geopokrovskiy.dto.invitation.InvitationCreateRequestDto;
import com.geopokrovskiy.dto.invitation.InvitationResponseDto;
import com.geopokrovskiy.dto.merchant.MerchantCreateRequestDto;
import com.geopokrovskiy.dto.merchant.MerchantResponseDto;
import com.geopokrovskiy.dto.person_service.invitation.InvitationCreateRequestDto;
import com.geopokrovskiy.dto.person_service.invitation.InvitationResponseDto;
import com.geopokrovskiy.dto.person_service.merchant.MerchantResponseDto;
import com.geopokrovskiy.dto.person_service.user.MerchantMemberCreateRequestDto;
import com.geopokrovskiy.dto.person_service.user.MerchantMemberResponseDto;
import com.geopokrovskiy.dto.user.MerchantMemberCreateRequestDto;
import com.geopokrovskiy.dto.user.MerchantMemberResponseDto;
import com.geopokrovskiy.mapper.invitation.InvitationMapper;
import com.geopokrovskiy.mapper.merchant.MerchantMapper;
import com.geopokrovskiy.mapper.user.MerchantMemberMapper;
import com.geopokrovskiy.service.InvitationService;
import com.geopokrovskiy.service.MerchantMemberService;
import com.geopokrovskiy.service.MerchantService;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/user/merchants")
@Data
public class MerchantController {

    private final MerchantService merchantService;
    private final InvitationService invitationService;
    private final MerchantMemberService merchantMemberService;

    private final MerchantMapper merchantMapper;
    private final InvitationMapper invitationMapper;
    private final MerchantMemberMapper merchantMemberMapper;

    @PostMapping
    public ResponseEntity<Mono<com.geopokrovskiy.dto.person_service.merchant.MerchantResponseDto>> addNewMerchant(@RequestBody MerchantMemberCreateRequestDto merchantCreateRequestDto) {
        return new ResponseEntity<>(merchantService.addNewMerchant(merchantMapper.map(merchantCreateRequestDto)).map(merchantMapper::map), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mono<com.geopokrovskiy.dto.person_service.merchant.MerchantResponseDto>> getMerchantById(@PathVariable("id") UUID id) {
        return new ResponseEntity<>((merchantService.getMerchantById(id).map(merchantMapper::map)), HttpStatus.OK);
    }

    @PostMapping("/create_invitation")
    public ResponseEntity<Mono<com.geopokrovskiy.dto.person_service.invitation.InvitationResponseDto>> addNewInvitation(@RequestBody com.geopokrovskiy.dto.person_service.invitation.InvitationCreateRequestDto invitationCreateRequestDto) {
        return new ResponseEntity<>(invitationService.addNewInvitation(invitationMapper.map(invitationCreateRequestDto)).map(invitationMapper::map), HttpStatus.CREATED);
    }

    @PostMapping("/create_merchant_member")
    public ResponseEntity<Mono<MerchantMemberResponseDto>> addNewMerchantMember(@RequestBody MerchantMemberCreateRequestDto merchantMemberCreateRequestDto) {
        return new ResponseEntity<>(merchantMemberService.addNewMerchantMember(merchantMemberCreateRequestDto.getInvitationId(), merchantMemberMapper.map(merchantMemberCreateRequestDto).getAddress())
                .map(merchantMemberMapper::map), HttpStatus.CREATED);
    }
}
