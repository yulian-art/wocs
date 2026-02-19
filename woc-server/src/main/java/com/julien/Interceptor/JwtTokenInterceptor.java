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
        
        // 检查token是否存在
        if (token == null || token.trim().isEmpty()) {
            log.warn("请求头中缺少token");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        try {
            //用存储的key解密截取的token
            Claims claims = JwtUtil.parseJWT(jwtProperties.getSecretKey(), token);

            String userCode = claims.get(JwtClaimsConstant.USER_CODE).toString();
            Integer role = Integer.valueOf(claims.get(JwtClaimsConstant.ROLE).toString());
            Integer comId = Integer.valueOf(claims.get(JwtClaimsConstant.COM_ID).toString());
            BaseContext.setCurrentId(userCode);
            BaseContext.setCurrentRole(role);
            BaseContext.setCurrentComId(comId);
            log.info("用户认证成功，用户ID: {}, 角色: {}", userCode, role);
            return true;
        } catch (Exception ex) {
            log.error("token解析失败: ", ex);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            //token解析失败
            return false;
        }
    }
}
