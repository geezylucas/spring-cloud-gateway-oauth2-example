package com.geezylucas.oauth2gateway.filter;

import cn.hutool.core.text.CharSequenceUtil;
import com.nimbusds.jose.JWSObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.text.ParseException;

@Slf4j
@Component
public class AuthGlobalFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String token = exchange.getRequest().getHeaders().getFirst("Authorization");
        if (token == null || CharSequenceUtil.isEmpty(token)) {
            return chain.filter(exchange);
        }

        try {
            String realToken = token.replace("Bearer ", "");
            JWSObject jwsObject = JWSObject.parse(realToken);
            String user = jwsObject.getPayload().toString();
            log.info("AuthGlobalFilter.filter() user:{}", user);
            ServerHttpRequest request = exchange.getRequest().mutate().header("user", user).build();
            exchange = exchange.mutate().request(request).build();
        } catch (ParseException ex) {
            ex.printStackTrace();
        }

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }

}
