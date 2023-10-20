//package com.metoo.nspm.core.config.cas.webflow;
//
//import lombok.extern.slf4j.Slf4j;
//import org.apereo.cas.authentication.AuthenticationHandlerExecutionResult;
//import org.apereo.cas.authentication.Credential;
//import org.apereo.cas.authentication.PreventedException;
//import org.apereo.cas.authentication.handler.support.AbstractPreAndPostProcessingAuthenticationHandler;
//import org.apereo.cas.authentication.principal.Principal;
//import org.apereo.cas.authentication.principal.PrincipalFactory;
//import org.apereo.cas.authentication.principal.Service;
//import org.apereo.cas.services.ServicesManager;
//
//import javax.security.auth.login.FailedLoginException;
//import java.security.GeneralSecurityException;
//
//@Slf4j
//public class MobileHandler extends AbstractPreAndPostProcessingAuthenticationHandler {
//
//    public MobileHandler(String name, ServicesManager servicesManager, PrincipalFactory principalFactory, Integer order ) {
//        super(name, servicesManager, principalFactory, order);
//
//    }
//
////    @Override
////    protected AuthenticationHandlerExecutionResult doAuthentication(Credential credential, Service service)
////            throws GeneralSecurityException, PreventedException {
////
////            MobileIdCredential mobileIdCredential = (MobileIdCredential) credential;
////
////            String phoneNumber = mobileIdCredential.getPhoneNumber();
////            String validataCode = mobileIdCredential.getValidataCode();
////            System.out.println(phoneNumber);
////            System.out.println(validataCode);
//////        Puser puser = null;
////            if(phoneNumber==null){
////                log.debug("手机号码为null");
////                throw new FailedLoginException("手机号码必须填写");
////            }
////            //TODO 这里做手机验证码校验操作
//////        throw new FailedLoginException("手机号码必须填写");
////
////            Principal principal = principalFactory.createPrincipal(credential.getId());
////
////            return  createHandlerResult(credential,
////                    principal);
////
////        }
//
//    //@Autowired
//    //UserRepository jpaMobileUserRepository;
//
//    @Override
//    public boolean supports(Credential credential) {
//        //判断传递过来的Credential 是否是自己能处理的类型
//        return credential instanceof MobileIdCredential;
//    }
//
//    @Override
//    protected AuthenticationHandlerExecutionResult doAuthentication(Credential credential) throws GeneralSecurityException,
//            PreventedException {
//        MobileIdCredential mobileIdCredential = (MobileIdCredential) credential;
//
//
//
//        String phoneNumber = mobileIdCredential.getPhoneNumber();
//        String validataCode = mobileIdCredential.getValidataCode();
//        System.out.println(phoneNumber);
//        System.out.println(validataCode);
////        Puser puser = null;
//        if(phoneNumber==null){
//            log.debug("手机号码为null");
//            throw new FailedLoginException("手机号码必须填写");
//        }
//        //TODO 这里做手机验证码校验操作
////        throw new FailedLoginException("手机号码必须填写");
//
//        Principal principal = principalFactory.createPrincipal(credential.getId());
//
//        return  createHandlerResult(credential,
//                principal);
//
//    }
//}
