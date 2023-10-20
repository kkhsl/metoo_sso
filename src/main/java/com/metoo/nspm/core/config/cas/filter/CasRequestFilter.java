package com.metoo.nspm.core.config.cas.filter;

import com.metoo.nspm.core.config.cas.threadLocal.RequestHolder;
import com.metoo.nspm.core.config.utils.ResponseUtil;
import com.metoo.nspm.core.service.ILoginLogService;
import com.metoo.nspm.core.service.IVerifyCodeService;
import com.metoo.nspm.vo.Result;
import com.metoo.nspm.entity.VerifyCode;
import org.jasig.cas.client.validation.AssertionImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Order(-1)
@WebFilter(urlPatterns = "/admin/*", filterName = "casRequestFilter")
public class CasRequestFilter implements Filter {

    Logger log = LoggerFactory.getLogger(CasRequestFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("casRequestFilter被初始化了");
    }

    @Autowired
    private IVerifyCodeService verifyCodeService;
    @Autowired
    private ILoginLogService loginLogService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession();

        AssertionImpl assertion= (AssertionImpl) session.getAttribute("_const_cas_assertion_");
        log.info("进入filter");
        if(assertion != null){
            log.info("进入filter - assertion");
            if(assertion.getPrincipal() != null){

                log.info("进入filter - Principal");

                String userName = assertion.getPrincipal().toString();

                VerifyCode verifyCode = this.verifyCodeService.selectObjByMobile(userName);
                if(verifyCode != null){
                    try {
                        this.verifyCodeService.delete(verifyCode.getMobile());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    // 记录登录日志
//                    this.loginLogService.save(null);
                }

                log.info("进入filter - userName:" + userName + ";");

                RequestHolder.add(userName);
                RequestHolder.add(request);
                filterChain.doFilter(request, response);



                return;
            }
        }else{
            ResponseUtil.out(response, Result.build(401, "未认证 filter"));
        }
    }



    @Override
    public void destroy() {
        System.out.println("casRequestFilter被销毁了");
    }
}
