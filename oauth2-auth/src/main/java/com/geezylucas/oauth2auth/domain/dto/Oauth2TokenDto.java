package com.geezylucas.oauth2auth.domain.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
@Builder
public class Oauth2TokenDto {

    private String token;
    private String refreshToken;
    private String tokenHead;
    private int expiresIn;

}