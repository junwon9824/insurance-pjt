package com.pjt.insurance.user.model.dto.response;

import com.pjt.insurance.user.model.entity.MemberProfile;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserResponse {

    private Long id;
    private String email;
    private String role;
    private String nickname;
    private String thumbnail;
    private String rank;
    private String location;
    private String authProvider;


    public static MemberProfile toEntity(UserResponse userResponse){
        return  MemberProfile.builder()
                .email(userResponse.email)
                .id(userResponse.id)
                .location(userResponse.location)
                .nickname(userResponse.nickname)
                .rank(userResponse.rank)
                .role(userResponse.role)
                .thumbnail(userResponse.thumbnail)
                .authProvider(userResponse.authProvider)
                .build();
    }
}
