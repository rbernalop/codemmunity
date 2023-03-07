package org.rbernalop.apigateway.security.infrastructure;

import lombok.AllArgsConstructor;
import org.rbernalop.shared.domain.SecurityUser;
import org.rbernalop.shared.infrastructure.configuration.JwtConfiguration;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.GatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@AllArgsConstructor
public class AuthorizedFilter implements GatewayFilterFactory<AuthorizedFilter.Config> {

    private final JwtConfiguration jwtConfiguration;

    public static class Config {}

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            SecurityUser user = JwtTokenManager.getSecurityUserFromBearerToken(request.getHeaders().getFirst("Authorization"), jwtConfiguration.getKey());

            if(user == null) {
                response.setStatusCode(org.springframework.http.HttpStatus.UNAUTHORIZED);
                return response.setComplete();
            }

            System.out.println("Usuario: " + user.getUsername());

            UriComponentsBuilder builder = UriComponentsBuilder.fromUri(request.getURI()).queryParam("user", user.getUsername());
            request = request.mutate().uri(builder.build().toUri()).build();

            return chain.filter(exchange.mutate().response(response).request(request).build());
        };
    }

    @Override
    public Class<Config> getConfigClass() {
        return Config.class;
    }

}

