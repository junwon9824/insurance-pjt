package com.pjt.insurance.user.model.entity;

import com.pjt.insurance.user.model.dto.response.UserResponse;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "member_profile")
public class MemberProfile implements UserDetails {

    // DB 필드
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long id;

    @Column(name = "user_email", nullable = false, length = 50)
    private String email;

    @Column(name = "user_pwd", nullable = false, length = 255)
    private String password;

    @Column(name = "user_role", nullable = false, length = 20)
    private String role;

    @Column(name = "user_nick", nullable = false, length = 10)
    private String nickname;

    @Column(name = "user_thumb", length = 255)
    private String thumbnail;

    @Column(name = "user_rank", length = 20)
    private String rank;

    @Column(name = "user_location", length = 20)
    private String location;

    @Column(name = "user_provider", nullable = false, length = 20)
    private String authProvider;


    // 메서드 설정


    @Override
    public String getUsername() {
        return this.email;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(this.role));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // 실제 로직에 맞게 수정
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // 실제 로직에 맞게 수정
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // 실제 로직에 맞게 수정
    }

    @Override
    public boolean isEnabled() {
        return true; // 실제 로직에 맞게 수정
    }


    // DTO 생성자
    public UserResponse toResponse() {
        return UserResponse.builder()
                .id(this.id)
                .email(this.email)
                .role(this.role)
                .nickname(this.nickname)
                .thumbnail(this.thumbnail)
                .rank(this.rank)
                .location(this.location)
                .authProvider(this.authProvider)
                .build();
    }


    public static MemberProfile of(OAuth2User oAuth2User) {
        Map<String, Object> map = oAuth2User.getAttributes();
        return MemberProfile.builder()
                .email((String) map.get("email"))
                .nickname((String) map.get("nickname")) // 예시입니다. 실제 속성명에 맞게 조정 필요
                .role("ROLE_MEMBER")
                .thumbnail((String) map.get("picture"))
                .authProvider(((String) map.get("authProvider")).toUpperCase())
                .build();
    }


}
