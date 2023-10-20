package com.metoo.nspm.core.config.cas.strategy;

import com.metoo.nspm.core.config.utils.ResponseUtil;
import com.metoo.nspm.vo.Result;
import org.apache.logging.log4j.core.config.Order;
import org.jasig.cas.client.authentication.AuthenticationRedirectStrategy;
import org.jasig.cas.client.validation.AssertionImpl;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
@Order(2)
public class ClientAuthRedirectStrategy implements AuthenticationRedirectStrategy {


    @Override
    public void redirect(HttpServletRequest request, HttpServletResponse response,
                         String potentialRedirectUrl) throws IOException {
//        httpServletResponse.setCharacterEncoding("utf-8");
//        httpServletResponse.setContentType("application/json; charset=utf-8");
//        PrintWriter out = httpServletResponse.getWriter();
        // 返回状态码
//        ResponseUtil.out(httpServletResponse,
//                Result.build(401, "未认证", "https://java1234.com:18443/cas/login?service="));

        System.out.println(potentialRedirectUrl);

        HttpSession session = request.getSession();
        AssertionImpl assertion= (AssertionImpl) session.getAttribute("_const_cas_assertion_");
        response.setHeader("Content-type", "application/json;charset=UTF-8");
        response.setCharacterEncoding("utf-8");
        response.setStatus(200);
        if (assertion != null) {
//            try {
//                response.setHeader("Content-type", "text/html;charset=UTF-8");
//                response.sendRedirect(Globals.AUTH_URL);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }

        } else {
            ResponseUtil.out(response, Result.build(401, "未认证"));

//            response.setHeader("Content-type", "text/html;charset=UTF-8");
//            response.sendRedirect(Globals.AUTH_URL);
        }
//        out.write("401");
    }
}
