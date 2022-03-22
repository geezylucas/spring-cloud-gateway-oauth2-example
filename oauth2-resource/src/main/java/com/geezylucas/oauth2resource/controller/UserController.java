package com.geezylucas.oauth2resource.controller;

import cn.hutool.core.convert.Convert;
import cn.hutool.json.JSONObject;
import com.geezylucas.oauth2resource.domain.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * RestController para obtener información de usuario de inicio de sesión
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/currentUser")
    public User currentUser(HttpServletRequest request) {
        String user = request.getHeader("user");
        JSONObject userJsonObject = new JSONObject(user);
        return User.builder()
                .username(userJsonObject.getStr("user_name"))
                .id(Convert.toLong(userJsonObject.get("id")))
                .roles(Convert.toList(String.class, userJsonObject.get("authorities")))
                .build();
    }

    @GetMapping
    public JSONObject findUser(HttpServletRequest request) {
        return new JSONObject(request.getHeader("user"));
    }

}
