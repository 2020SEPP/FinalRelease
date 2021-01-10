package com.pclogo.demo.Interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pclogo.demo.utils.JwtToken;
import com.pclogo.demo.utils.UserUtil;
import io.jsonwebtoken.Claims;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class SessionInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception
    {
        String token = request.getHeader("token");
        if(token == null || token.equals(""))
        {
            System.out.println("是空的");
            return false;
        }
        Claims claims = null;
        claims = JwtToken.parseJWT(token);
        if(claims == null) return false;
        String subject = claims.getSubject();
        JSONObject jsonObject = JSON.parseObject(subject);
        UserUtil userUtil = jsonObject.toJavaObject(UserUtil.class);
        return userUtil.getUid() != null;
    }
}
