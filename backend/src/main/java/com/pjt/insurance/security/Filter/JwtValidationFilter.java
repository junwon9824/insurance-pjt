package com.pjt.insurance.security.Filter;

import com.pjt.insurance.security.model.entity.CustomAuthenticationToken;
import com.pjt.insurance.security.util.JwtUtils;
import com.pjt.insurance.user.repository.MemberRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtValidationFilter extends OncePerRequestFilter {
    private final MemberRepository userRepository;
    private final JwtUtils jwtUtils;
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String BEARER_PREFIX = "Bearer ";

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION_HEADER);


        // 코드 리뷰 버전
        String[] list = StringUtils.split(authorizationHeader, BEARER_PREFIX);

        String path = request.getServletPath();

        // 특정 경로에 대해서는 JWT 검증을 건너뜁니다.
        if (path.startsWith("/v3/api-docs")) {
            filterChain.doFilter(request, response); // JWT 검증 없이 다음 필터로 이동
            return;
        }

        //authorizationHeader 가 null 이 아니고 authorizationHeader에 앞에 BEARER_PREFIX 가 있을 경우만 accessToken 값 있음, 아니면 null
        String accessToken = (list != null && list.length == 2 && list[0].isEmpty()) ? list[1] : null;

        if (accessToken == null) {
            System.out.println("requestrequest"+request.getRequestURI());
            doFilter(request, response, filterChain);
            return;
        }

        //엑세스 토큰 검증
        Jws<Claims> claimsJws = jwtUtils.validateAccessToken(accessToken);

        //토큰 있으면 검증
        if (claimsJws != null) {
            // 각 권한 문자열을 SimpleGrantedAuthority 객체로 변환
            List<String> roles = claimsJws.getBody().get("authorities", List.class);
            Collection<GrantedAuthority> authorities = roles.stream()
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());

            CustomAuthenticationToken customAuthenticationToken =
                    new CustomAuthenticationToken(
                            jwtUtils.getEmailByAccessToken(accessToken),
                            jwtUtils.getUserIdByAccessToken(accessToken),
                            null,
                            authorities
                    ); // principal,userid,password,authorities 가 들어감}

            SecurityContextHolder.getContext().setAuthentication(customAuthenticationToken);
            log.info("jwt 토큰 검증 : {}", SecurityContextHolder.getContext().toString());

        }

        filterChain.doFilter(request, response);
    }

}
