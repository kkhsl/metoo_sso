package com.metoo.nspm.core.jwt.interceptor;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.metoo.nspm.core.jwt.util.JwtUtil;
import com.metoo.nspm.core.service.AuthCodeService;
import com.metoo.nspm.entity.nspm.AuthCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *     Title: JwtInterceptor.java
 * </p>
 *
 * <p>
 *     Description: JWT 登录拦截器
 * </p>
 *
 * <author>
 *     HKK
 * </author>
 */

public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private AuthCodeService authCodeService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        String token = request.getHeader("token");
        String tokenHead = request.getHeader("Authorization");

        String verify_code = request.getHeader("code");

        //token头不等于空 并且以Bearer 开头进行token验证登录处理
        int code = 200;
        Map map = new HashMap();
        if(tokenHead == null || !tokenHead.startsWith("Bearer ")){
            code = 501;
            map.put("msg", "无效签名");
        }else{
            String token = tokenHead.replace("Bearer ", "");
            try {
                JwtUtil.verifyJwt(token);// 验证令牌
                return true;
            } catch (SignatureVerificationException e) {
                e.printStackTrace();
                map.put("msg","无效签名");
                code = 1001;
                System.out.println("无效签名");
            }catch (TokenExpiredException e){
                e.printStackTrace();
                code = 1002;
                map.put("msg","token 过期");
            }catch (AlgorithmMismatchException e){
                e.printStackTrace();
                code = 1003;
                map.put("msg","算法不一致");
                System.out.println("算法不一致");
            }catch (Exception e){
                e.printStackTrace();
                code = 1004;
                map.put("msg","token 无效");
                System.out.println("token 无效");
            }
        }
        map.put("code", code);
        String json  = new ObjectMapper().writeValueAsString(map);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(json);
        return false;
    }

    // 预处理回调方法
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
////        String token = request.getHeader("token");
//        String tokenHead = request.getHeader("Authorization");
//
//        String verify_code = request.getHeader("code");
//
//       //token头不等于空 并且以Bearer 开头进行token验证登录处理
//        int code = 200;
//        Map map = new HashMap();
//        if(tokenHead == null || !tokenHead.startsWith("Bearer ")){
//            code = 501;
//            map.put("msg", "无效签名");
//        }else{
//            String token = tokenHead.replace("Bearer ", "");
//            try {
//                JwtUtil.verifyJwt(token);// 验证令牌
//
//                DecodedJWT dj = JwtUtil.getDecodedJWT(token);
//
//                // 判断用户是否退出
//                String djCode = dj.getClaim("code").asString();
//                AuthCode authCode = this.authCodeService.selectObjByCode(djCode);
//                if(authCode == null){
//                    code = 1004;
//                    map.put("msg","token 无效");
//                }else{
//                    // 计算当前时间是否超过过期时间的一半，如果是就帮用户续签 --------------------------
//                    // 此处并不是永久续签，只是为 大于过期时间的一半 且 小于过期时间 的 token 续签
//                    Long expTime = dj.getExpiresAt().getTime();
//                    Long iatTime = dj.getIssuedAt().getTime();
//                    Long nowTime = System.currentTimeMillis();
//                    if((nowTime-iatTime) > (expTime-iatTime) / 2) {
//                        // 生成新的jwt
//                        Map<String, String> payload = new HashMap<>();
//                        payload.put("code", verify_code);
//                        String newJwt = JwtUtil.getToken(payload);
//                        // 加入返回头
//                        response.addHeader("Authorization", "Bearer " + newJwt);
//                        if(verify_code == null && verify_code != ""){
//                            // 根据token查询 refresh_token
//                            AuthCode refresh = this.authCodeService.selectObjByToken(token);
//                            if(refresh != null){
//                                // 更新token
//                                refresh.setRefresh_token(newJwt);
//                                this.authCodeService.update(refresh);
//                            }
//                        }
//                    }
//                    return true;
//                }
//            } catch (SignatureVerificationException e) {
//                e.printStackTrace();
//                map.put("msg","无效签名");
//                code = 1001;
//                System.out.println("无效签名");
//            }catch (TokenExpiredException e){
//                e.printStackTrace();
//                if(verify_code == null && verify_code.equals("")){
//                    // 根据token查询refresh_token
//                    AuthCode refresh = this.authCodeService.selectObjByToken(token);
//                    if(refresh != null){
//                        if(refresh.getRefresh_token() != null && !refresh.getRefresh_token().equals("")){
//                            // 校验刷新token是否失效
//                            try {
//                                JwtUtil.verifyJwt(refresh.getRefresh_token());// 验证令牌
//                                response.addHeader("Authorization", "Bearer " + refresh.getRefresh_token());
//                                return true;
//                            } catch (TokenExpiredException e1) {
//                                e1.printStackTrace();
//                            }
//                        }
//                    }
//                }
//                code = 1002;
//                map.put("msg","token 过期");
//            }catch (AlgorithmMismatchException e){
//                e.printStackTrace();
//                code = 1003;
//                map.put("msg","算法不一致");
//                System.out.println("算法不一致");
//            }catch (Exception e){
//                e.printStackTrace();
//                code = 1004;
//                map.put("msg","token 无效");
//                System.out.println("token 无效");
//            }
//        }
//        map.put("code", code);
//        String json  = new ObjectMapper().writeValueAsString(map);
//        response.setContentType("application/json;charset=UTF-8");
//        response.getWriter().print(json);
//        return false;
//    }

    // 目标方法执行后调用
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
        System.out.println("");
    }

}
