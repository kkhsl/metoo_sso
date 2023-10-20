package com.metoo.nspm.core.mapper;

import com.metoo.nspm.entity.VerifyCode;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VerifyCodeMapper {

    VerifyCode selectObjByMobile(String mobile);

    int save(VerifyCode instance);

    int update(VerifyCode instance);


    int delete(String mobile);
}
