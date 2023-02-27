package com.sisyphus.provider.uac.util;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * @author Zhecheng Zhao
 * @RegistrationNo 220186627
 * @date Created in 24/02/2023 16:24
 */
public class WebFlux {

    public static String get(String url) {
        Mono<String> resp = WebClient.create()
                .method(HttpMethod.GET)
                                    .uri(url)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve().bodyToMono(String.class);
        return resp.block();
    }

}
