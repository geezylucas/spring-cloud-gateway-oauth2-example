package com.geezylucas.oauth2auth.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.geezylucas.oauth2auth.constant.RedisConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
@RequiredArgsConstructor
public class ResourceServiceImpl {

    private final RedisTemplate<String, Object> redisTemplate;

    @PostConstruct
    public void initData() {
        Map<String, List<String>> resourceRolesMap = new TreeMap<>();
        resourceRolesMap.put("/resource/hello", CollUtil.toList("ADMIN"));
        resourceRolesMap.put("/resource/user/currentUser", CollUtil.toList("ADMIN", "USER"));

        redisTemplate.opsForHash().putAll(RedisConstant.RESOURCE_ROLES_MAP, resourceRolesMap);
    }

}
