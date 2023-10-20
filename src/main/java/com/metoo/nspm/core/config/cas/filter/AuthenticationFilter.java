package com.metoo.nspm.core.config.cas.filter;

import org.jasig.cas.client.Protocol;
import org.jasig.cas.client.authentication.AuthenticationRedirectStrategy;
import org.jasig.cas.client.authentication.DefaultAuthenticationRedirectStrategy;
import org.jasig.cas.client.configuration.ConfigurationKeys;
import org.jasig.cas.client.util.AbstractCasFilter;
import org.jasig.cas.client.util.CommonUtils;
import org.jasig.cas.client.util.ReflectUtils;
import org.jasig.cas.client.validation.Assertion;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthenticationFilter extends AbstractCasFilter {

    /**
     * 默认的重定向策略，详见下方DefaultAuthenticationRedirectStrategy源码
     */
    private AuthenticationRedirectStrategy authenticationRedirectStrategy = new DefaultAuthenticationRedirectStrategy();

    protected AuthenticationFilter(Protocol protocol) {
        super(protocol);
    }


    @Override
    protected void initInternal(final FilterConfig filterConfig) throws ServletException {
        if (!isIgnoreInitConfiguration()) {
            super.initInternal(filterConfig);

            //从用户给定的authenticationRedirectStrategyClass参数中获取类的全限定名并获取其Class对象，然后通过反射创建实例
            final Class<? extends AuthenticationRedirectStrategy> authenticationRedirectStrategyClass =
                    getClass(ConfigurationKeys.AUTHENTICATION_REDIRECT_STRATEGY_CLASS);

            if (authenticationRedirectStrategyClass != null) {
                this.authenticationRedirectStrategy = ReflectUtils.newInstance(authenticationRedirectStrategyClass);
            }
        }
    }


    @Override
    public final void doFilter(final ServletRequest servletRequest, final ServletResponse servletResponse,
                               final FilterChain filterChain) throws IOException, ServletException {

        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;
        //从请求参数中获取ticket

        filterChain.doFilter(request, response);

    }

    protected void onSuccessfulValidation(final HttpServletRequest request, final HttpServletResponse response,
                                          final Assertion assertion) {
        // nothing to do here.
        System.out.println("GroupType");
    }

//    @Override
//    public final void doFilter(final ServletRequest servletRequest, final ServletResponse servletResponse,
//                               final FilterChain filterChain) throws IOException, ServletException {
//
//        final HttpServletRequest request = (HttpServletRequest) servletRequest;
//        final HttpServletResponse response = (HttpServletResponse) servletResponse;
//
//        final HttpSession session = request.getSession(false);
//        final Assertion assertion = session != null ? (Assertion) session.getAttribute(CONST_CAS_ASSERTION) : null;
//
//        //CAS已登录的判断条件是：session存在且包含用户信息
//        if (assertion != null) {
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//        //生成重定向的URL
//        final String urlToRedirectTo = "https://java1234.com:8443/cas/login?service=http://java1234.com:8089/cas/admin/client2";
//        //重定向到CAS登录页面
//        this.authenticationRedirectStrategy.redirect(request, response, urlToRedirectTo);
//    }
}
