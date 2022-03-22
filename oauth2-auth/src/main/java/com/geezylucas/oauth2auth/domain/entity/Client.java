package com.geezylucas.oauth2auth.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@Builder(toBuilder = true)
public class Client implements Serializable {

    private static final long serialVersionUID = 2405172041950251807L;

    private String clientId;
    private String resourceIds;
    private Boolean secretRequire;
    private String clientSecret;
    private Boolean scopeRequire;
    private String scope;
    private String authorizedGrantTypes;
    private String webServerRedirectUri;
    private String authorities;
    private Integer accessTokenValidity;
    private Integer refreshTokenValidity;

}
