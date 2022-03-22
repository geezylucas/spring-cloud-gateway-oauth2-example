package com.geezylucas.oauth2auth.exception;

import com.geezylucas.oauth2auth.api.CommonResult;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Manejar excepciones lanzadas por Oauth2 globalmente
 */
@Controller
public class Oauth2ExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = OAuth2Exception.class)
    public CommonResult<String> handleOauth2(OAuth2Exception ex) {
        return CommonResult.failed(ex.getMessage());
    }

}
