//package com.metoo.nspm.core.config.cas.test;
//
//import org.jasig.cas.client.authentication.*;
//import org.jasig.cas.client.util.AbstractCasFilter;
//import org.jasig.cas.client.util.CommonUtils;
//import org.jasig.cas.client.validation.Assertion;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.HashMap;
//import java.util.Map;
//
//public class MyAuthenticationFilter extends AuthenticationFilter {
//
//
//    public final void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
//                               FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest request = (HttpServletRequest)servletRequest;
//        HttpServletResponse response = (HttpServletResponse)servletResponse;
//        if (this.isRequestUrlExcluded(request)) {
//            this.logger.debug("Request is ignored.");
//            filterChain.doFilter(request, response);
//        } else {
//            HttpSession session = request.getSession(false);
//            Assertion assertion = session != null ? (Assertion)session.getAttribute("_const_cas_assertion_") : null;
//            if (assertion != null) {
//                filterChain.doFilter(request, response);
//            } else {
//                String serviceUrl = this.constructServiceUrl(request, response);
//                String ticket = this.retrieveTicketFromRequest(request);
//                boolean wasGatewayed = this.gateway && this.gatewayStorage.hasGatewayedAlready(request, serviceUrl);
//                if (!CommonUtils.isNotBlank(ticket) && !wasGatewayed) {
//                    this.logger.debug("no ticket and no assertion found");
//                    String modifiedServiceUrl;
//                    if (this.gateway) {
//                        this.logger.debug("setting gateway attribute in session");
//                        modifiedServiceUrl = this.gatewayStorage.storeGatewayInformation(request, serviceUrl);
//                    } else {
//                        modifiedServiceUrl = serviceUrl;
//                    }
//
//                    this.logger.debug("Constructed service url: {}", modifiedServiceUrl);
////                    String urlToRedirectTo = CommonUtils.constructRedirectUrl(this.casServerLoginUrl, this.getProtocol().getServiceParameterName(), modifiedServiceUrl, this.renew, this.gateway);
////                    this.logger.debug("redirecting to \"{}\"", urlToRedirectTo);
////                    this.authenticationRedirectStrategy.redirect(request, response, urlToRedirectTo);
//                    response.setCharacterEncoding("UTF-8");
//                    PrintWriter out = response.getWriter();
//                    response.setContentType("application/json; charset=UTF-8");
//                    out.println("{\"code\":\"403\",\"message\":\"请登陆！\"}");
//                } else {
//                    filterChain.doFilter(request, response);
//                }
//            }
//        }
//    }
//}
