package com.metoo.nspm.core.config.cas.filter;

import com.metoo.nspm.core.config.cas.threadLocal.RequestHolder;
import com.metoo.nspm.core.config.utils.ResponseUtil;
import com.metoo.nspm.core.service.ILoginLogService;
import com.metoo.nspm.entity.VerifyCode;
import com.metoo.nspm.vo.Result;
import lombok.SneakyThrows;
import org.jasig.cas.client.validation.Assertion;
import org.jasig.cas.client.validation.Cas20ProxyReceivingTicketValidationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 票据校验过滤器
public class ClientCas20ProxyReceivingTicketValidationFilter extends Cas20ProxyReceivingTicketValidationFilter {

        public ClientCas20ProxyReceivingTicketValidationFilter(){}

        private ILoginLogService loginLogService;

        public ILoginLogService getLoginLogService(){
            return this.loginLogService;
        }

        public ClientCas20ProxyReceivingTicketValidationFilter(ILoginLogService loginLogService){
            this.loginLogService = loginLogService;
        }

        @SneakyThrows
        @Override
        protected void onSuccessfulValidation(HttpServletRequest request, HttpServletResponse response,
                                              Assertion assertion) {
            //重定向到前端首页。此处为方便阅读使用了硬编码，实际应用时，应写入配置文件
//            response.reset();
//            response.sendRedirect("java1234.com");
//            HttpSession session = request.getSession();
//            System.out.println(session.getId());
//            System.out.println(request.getRequestedSessionId());
//            ResponseUtil.out(response, Result.build(200, "认证通过"));
            if(assertion != null){
                if(assertion.getPrincipal() != null){
                    String userName = assertion.getPrincipal().toString();
                    RequestHolder.add(userName);
                    RequestHolder.add(request);
                }
            }
            this.loginLogService.save(null);

        }

    @Override
    public void onFailedValidation(HttpServletRequest request, HttpServletResponse response) {
        logger.info("Failed to validate cas ticket");
        System.out.println("认证失败");
    }
}
