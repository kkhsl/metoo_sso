package com.metoo.nspm.core.config.cas.manager;

import com.metoo.nspm.core.config.utils.ResponseUtil;
import com.metoo.nspm.vo.Result;
import org.jasig.cas.client.authentication.AttributePrincipal;
import org.jasig.cas.client.validation.AssertionImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class CasManagerController {
//
//    @Value("${cas.server-url-prefix}")
//    private String casServerUrlPrefix;

    @GetMapping("/auth")
    public void auth(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();
//        session._const_cas_assertion_.principal.name

        AssertionImpl assertion= (AssertionImpl) session.getAttribute("_const_cas_assertion_");

        AttributePrincipal principal = assertion.getPrincipal();


        response.setHeader("Content-type", "application/json;charset=UTF-8");
        response.setCharacterEncoding("utf-8");
        response.setStatus(200);
        if (assertion != null) {
            String redirectUrl= request.getParameter("redirectUrl");
            try {
                response.setHeader("Content-type", "text/html;charset=UTF-8");
                response.sendRedirect(redirectUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            ResponseUtil.out(response, Result.build(401, "未认证"));
        }
    }

//    @GetMapping("/logout")
//    public RedirectView logout(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
//        session.invalidate();
//        String indexPageUrl = "http://127.0.0.1";
//        return new RedirectView( casServerUrlPrefix + "/logout?service=" + indexPageUrl);
//    }

}
