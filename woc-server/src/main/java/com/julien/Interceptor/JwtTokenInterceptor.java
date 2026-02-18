package com.julien.Interceptor;

import com.julien.constant.JwtClaimsConstant;
import com.julien.context.BaseContext;
import com.julien.properties.JwtProperties;
import com.julien.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class JwtTokenInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtProperties jwtProperties;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){

        if(!(handler instanceof HandlerMethod)){
            //放行前端静态资源
            return true;
        }

        String token =  request.getHeader(jwtProperties.getTokenName());

        try {
            //用存储的key解密截取的token
            Claims claims = JwtUtil.parseJWT(jwtProperties.getSecretKey(), token);

            Long userCode = Long.valueOf(claims.get(JwtClaimsConstant.USER_CODE).toString());
            String role = claims.get(JwtClaimsConstant.ROLE).toString();
            BaseContext.setCurrentId(userCode);
            BaseContext.setCurrentRole(role);
            return true;
        } catch (Exception ex) {

            response.setStatus(401);
            //token解析失败
            return false;
        }
    }
}
