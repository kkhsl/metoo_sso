package com.metoo.nspm.core.config.utils;

import com.metoo.nspm.core.config.application.ApplicationContextUtils;
import com.metoo.nspm.core.service.IUserService;
import com.metoo.nspm.entity.nspm.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class ShiroUserHolder {

    @Autowired
    private IUserService userService;

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

//    public static User currentUser(HttpServletRequest request) {
//
//    }

}
