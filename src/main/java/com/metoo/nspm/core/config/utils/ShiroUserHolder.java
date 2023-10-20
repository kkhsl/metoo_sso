package com.metoo.nspm.core.config.utils;

import com.metoo.nspm.core.config.application.ApplicationContextUtils;
import com.metoo.nspm.core.config.cas.threadLocal.RequestHolder;
import com.metoo.nspm.core.service.IUserService;
import com.metoo.nspm.entity.User;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;

@Component
public class ShiroUserHolder {

//    public static User currentUser() {
//        if (SecurityUtils.getSubject() != null){
//            Subject subject = SecurityUtils.getSubject();
//            if(subject.getPrincipal() != null && subject.isAuthenticated()){
//                String userName = SecurityUtils.getSubject().getPrincipal().toString();
//                IUserService userService = (IUserService) ApplicationContextUtils.getBean("userServiceImpl");
//                 User user = userService.selectByName(userName);
//                if(user != null){
//                    return user;
//                }
//            }
//        }
//
//        return null;
//    }

    public static User currentUser() {
        String username = RequestHolder.getCurrentUser();
        if(Strings.isNotBlank(username)){
            IUserService userService = (IUserService) ApplicationContextUtils.getBean("userServiceImpl");
            User user = userService.selectObjByMobile(username);
            if(user == null){
                user = userService.selectByName(username);
            }
            return user;
        }
        return null;
    }
}
