package com.sword.security.handle;


import com.fasterxml.jackson.databind.json.JsonMapper;
import com.sword.constant.HttpStatus;
import com.sword.domain.AjaxResult;
import com.sword.utils.ServletUtils;
import com.sword.utils.StringUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 * 认证失败处理类 返回未授权
 *
 * @author ruoyi
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint, Serializable {
    private static final long serialVersionUID = -8970718410437077606L;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e)
            throws IOException {
        int code = HttpStatus.UNAUTHORIZED;
        String msg = StringUtils.format("请求访问：{}，认证失败，无法访问系统资源", request.getRequestURI());
        JsonMapper jsonMapper = new JsonMapper();
        ServletUtils.renderString(response, jsonMapper.writeValueAsString(AjaxResult.error(code, msg)));
    }
}
