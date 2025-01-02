package com.pjt.insurance.user.service;

import com.pjt.insurance.user.exception.UserErrorCode;
import com.pjt.insurance.user.exception.UserException;
import com.pjt.insurance.user.model.dto.request.UserRegisterRequest;
import com.pjt.insurance.user.model.dto.request.UserUpdateRequest;
import com.pjt.insurance.user.model.dto.response.UserResponse;
import com.pjt.insurance.user.model.entity.MemberProfile;
import com.pjt.insurance.user.model.entity.enums.UserRole;
import com.pjt.insurance.user.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final MemberRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public MemberProfile createUserEntity(UserRegisterRequest userRegisterRequest) {
        return MemberProfile.builder()
                .email(userRegisterRequest.getEmail())
                .password(passwordEncoder.encode(userRegisterRequest.getPassword()))
                .nickname(userRegisterRequest.getNickname())
                .role(String.valueOf(UserRole.ROLE_MEMBER))
                .authProvider(userRegisterRequest.getAuthProvider())
                .build();
    }

    public void registUser(UserRegisterRequest request) {
        // 등록하려는 유저정보가 이미 DB에 있으면 예외처리
        userRepository.findByEmail(request.getEmail()).ifPresent(u -> {
            throw new UserException(UserErrorCode.ALREADY_IN_EMAIL);
        });
        // 유저 객체를 DB에 저장
        userRepository.save(createUserEntity(request));
    }


//    public void registOauthUser(MemberProfile customUser) {
//        try {
//            userRepository.save(customUser);
//        } catch (RuntimeException e) {
//            throw new RuntimeException("오류 발생 : " + e);
//        }
//        log.info("유저 회원가입 성공 : {}", customUser.toString());
//
//    }

    public UserResponse getUserInfo() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = (String) principal;

        MemberProfile customUser = userRepository.findByEmail(email).orElseThrow(() -> new UserException(UserErrorCode.NOT_EXISTS_USER));
        return customUser.toResponse();
    }

    public MemberProfile getNowUserInfoEntityByToken() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = (String) principal;

        MemberProfile customUser = userRepository.findByEmail(email).orElseThrow(() -> new UserException(UserErrorCode.NOT_EXISTS_USER));
        return customUser;
    }



    public UserResponse getUserInfobyid(Long userid) {

        MemberProfile customUser = userRepository.findById(userid).orElseThrow(() -> new UserException(UserErrorCode.NOT_EXISTS_USER));
        return customUser.toResponse();
    }

    public MemberProfile getUserEntityById(Long userId) {
        MemberProfile customUser = userRepository.findById(userId).orElseThrow(() -> new UserException(UserErrorCode.NOT_EXISTS_USER));
        return customUser;
    }


    @Transactional
    public void updateCurrentUserInfo(UserUpdateRequest userUpdateRequest) {
        MemberProfile currentUser = getNowUserInfoEntityByToken();
        currentUser.setNickname(userUpdateRequest.getNewNickname());
        userRepository.save(currentUser);
        log.info("닉네임 수정 성공 : {}",userUpdateRequest.getNewNickname());
    }
}
