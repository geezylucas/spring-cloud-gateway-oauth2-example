package com.geezylucas.oauth2auth.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.geezylucas.oauth2auth.constant.MessageConstant;
import com.geezylucas.oauth2auth.domain.entity.Client;
import com.geezylucas.oauth2auth.service.ClientService;
import com.geezylucas.oauth2auth.service.principal.ClientPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private List<Client> clientList;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void initData() {
        String clientSecret = passwordEncoder.encode("123456");
        clientList = new ArrayList<>();
        clientList.add(Client.builder()
                .clientId("client-app")
                .resourceIds("oauth-resource")
                .secretRequire(false)
                .clientSecret(clientSecret)
                .scopeRequire(false)
                .scope("all")
                .authorizedGrantTypes("password,refresh_token")
                .authorities("ADMIN,USER")
                .accessTokenValidity(3600)
                .refreshTokenValidity(86400)
                .build());
    }

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        List<Client> findClientList = clientList.stream().filter(item -> item.getClientId().equals(clientId)).collect(Collectors.toList());
        if (CollUtil.isEmpty(findClientList)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, MessageConstant.NOT_FOUND_CLIENT);
        }
        return new ClientPrincipal(findClientList.get(0));
    }
}
