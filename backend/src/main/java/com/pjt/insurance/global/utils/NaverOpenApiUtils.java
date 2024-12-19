package com.pjt.insurance.global.utils;

import com.sail.back.global.config.NaverConfig;
import com.sail.back.product.model.dto.response.naver.SearchResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;



@Service
@Slf4j
@RequiredArgsConstructor
public class NaverOpenApiUtils {

    private final NaverConfig naverConfig;

    public Mono<SearchResult> sendSuchNaverShop(String suchText, Long start, Long display, String sort){

        WebClient webClient = WebClient.builder()
                .baseUrl("https://openapi.naver.com")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                .defaultHeader("X-Naver-Client-Id", naverConfig.getClientId())
                .defaultHeader("X-Naver-Client-Secret", naverConfig.getClientSecret())
                .build();

        Mono<SearchResult> response = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/v1/search/shop.json")
                        .queryParam("query", suchText)
                        .queryParam("start", start)
                        .queryParam("display", display)
                        .queryParam("sort", sort)
                        .build())
                .retrieve()
                .bodyToMono(SearchResult.class);

        return response;
    }
}
