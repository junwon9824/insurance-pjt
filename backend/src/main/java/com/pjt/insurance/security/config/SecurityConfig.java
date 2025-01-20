package com.pjt.insurance.security.config;


import com.pjt.insurance.security.Filter.JwtValidationFilter;
import com.pjt.insurance.security.handler.*;
import com.pjt.insurance.security.service.OAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Collections;

@RequiredArgsConstructor
@EnableWebSecurity(debug = false)
@Configuration
public class SecurityConfig {

    private final JwtValidationFilter jwtValidationFilter;
    private final AuthFailureHandler authFailureHandler;
    private final ExceptionHandlerFilter exceptionHandlerFilter;
    private final OAuthSuccessHandler oAuthSuccessHandler;
    private final OAuthService customOAuth2Service;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http

                // cors 설정
                .cors(corsConfigurer -> corsConfigurer.configurationSource(corsConfig()))
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
//                .oauth2Login(Customizer.withDefaults())

//                .oauth2Login(customizer ->
//                                customizer
////                                .failureHandler(authFailureHandler)
//                                        .userInfoEndpoint(userInfoEndpoint ->
//                                                userInfoEndpoint.userService(customOAuth2Service))
//                                        .successHandler(oAuthSuccessHandler)
//                )

                // 인가 경로 설정
                .authorizeHttpRequests((requests) ->
                        requests.requestMatchers(
                                "/error",
                                "/oauth2/**",
//                                "/login/**",
                                "/v1/auth/login",
                                "/v1/users/register",
                                "/v1/file/upload",
                                "/swagger-ui/**",
                                "swagger-ui**",
                                "/v3/api-docs/**",
                                "/v3/api-docs",
                                "/swagger-resources/**"
                        ).permitAll().anyRequest().authenticated())
                // 예외처리
//                .exceptionHandling(exceptionHandling ->
//                        exceptionHandling
//                                .authenticationEntryPoint(authFailureHandler)
//                )
//                // 토큰 사용을 위해 JSESSIONID 발급 중지
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // JWT 필터
                .addFilterBefore(jwtValidationFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(exceptionHandlerFilter, JwtValidationFilter.class);

        return http.build();
    }

    // authenticationManager 빈으로 등록
    @Bean
    public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    // CORS 설정
    public CorsConfigurationSource corsConfig() {
        return request -> {
            CorsConfiguration config = new CorsConfiguration();
            config.setAllowedHeaders(Collections.singletonList("*"));
            config.setAllowedMethods(Collections.singletonList("*"));
            config.setAllowedOrigins(Collections.singletonList("http://localhost:5173")); // 허용할 출처
            config.setAllowCredentials(true); // 인증 정보 허용
            config.setMaxAge(3600L);
            return config;
        };
    }
}
