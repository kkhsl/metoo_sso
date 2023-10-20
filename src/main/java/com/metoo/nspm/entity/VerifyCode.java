package com.metoo.nspm.entity;

import lombok.Data;

import java.util.Date;

@Data
public class VerifyCode {

    private Date addTime;
//    private String userName;// 索取验证码的用户名
    private String mobile;// 验证码对应的手机号码
    private String code;// 对应的验证码

}
