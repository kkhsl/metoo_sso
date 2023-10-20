//package com.metoo.nspm.core.config.cas.webflow;
//
//import org.apereo.cas.authentication.Credential;
//import org.apereo.cas.authentication.adaptive.AdaptiveAuthenticationPolicy;
//import org.apereo.cas.web.flow.actions.AbstractNonInteractiveCredentialsAction;
//import org.apereo.cas.web.flow.resolver.CasDelegatingWebflowEventResolver;
//import org.apereo.cas.web.flow.resolver.CasWebflowEventResolver;
//import org.apereo.cas.web.support.WebUtils;
//import org.springframework.webflow.execution.RequestContext;
//
//import javax.servlet.http.HttpServletRequest;
//
///**
// * @author Administrator
// */
//public class MobileIdCredentialsAction extends AbstractNonInteractiveCredentialsAction {
//
//    public MobileIdCredentialsAction(CasDelegatingWebflowEventResolver initialAuthenticationAttemptWebflowEventResolver,
//                                     CasWebflowEventResolver serviceTicketRequestWebflowEventResolver,
//                                     AdaptiveAuthenticationPolicy adaptiveAuthenticationPolicy) {
//        super(initialAuthenticationAttemptWebflowEventResolver, serviceTicketRequestWebflowEventResolver,
//                adaptiveAuthenticationPolicy);
//    }
//
//    @Override
//    protected Credential constructCredentialsFromRequest(RequestContext requestContext) {
//        try {
//            final HttpServletRequest request;
//            request = WebUtils.getHttpServletRequestFromExternalWebflowContext(requestContext);
//            MobileIdCredential credentials = new MobileIdCredential(request.getParameter("phoneNumber"), "");
//            if (credentials != null) {
////                LOGGER.debug("Received mobile authentication request from credentials [{}]", credentials);
//                return (Credential) credentials;
//            }
//        } catch (final Exception e) {
////            LOGGER.warn(e.getMessage(), e);
//            e.printStackTrace();
//        }
//        return null;
//    }
//}
