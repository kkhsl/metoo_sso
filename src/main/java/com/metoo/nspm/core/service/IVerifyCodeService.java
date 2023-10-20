package com.metoo.nspm.core.service;

import com.metoo.nspm.entity.VerifyCode;

public interface IVerifyCodeService {

    VerifyCode selectObjByMobile(String mobile);


    int update(String mobile, String code);


    int delete(String mobile);
}
