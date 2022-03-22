package com.geezylucas.oauth2auth.domain.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Oauth2 obtiene Token y devuelve encapsulado de información
 */
@Data
@EqualsAndHashCode
@Builder
public class Oauth2TokenDto {

    private String token;
    private String refreshToken;
    private String tokenHead;
    private int expiresIn;

}