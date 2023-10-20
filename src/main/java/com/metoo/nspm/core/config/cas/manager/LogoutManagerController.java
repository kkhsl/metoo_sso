package com.metoo.nspm.core.config.cas.manager;

import com.metoo.nspm.core.config.utils.ResponseUtil;
import org.apache.shiro.web.util.RedirectView;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class LogoutManagerController {

    @Value("${cas.server-url-prefix}")
    private String casServerUrlPrefix;

//
//    @GetMapping("/logout")
//    public RedirectView logout(HttpServletRequest request,
//                               HttpServletResponse response, HttpSession session) {
//        session.invalidate();
//        return new RedirectView( casServerUrlPrefix + "/logout");
//    }

    @RequestMapping("logout")
    @ResponseBody
    public Object logout(HttpSession session) {
        session.invalidate();
    // 使用cas退出成功后,跳转到http://cas.client1.com:9001/logout/success
//        return "redirect:" + casServerUrlPrefix + "/logout";
        return ResponseUtil.ok(casServerUrlPrefix + "/logout");
    }
}
