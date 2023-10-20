package com.metoo.nspm.core.service.impl;

import com.metoo.nspm.core.mapper.VerifyCodeMapper;
import com.metoo.nspm.core.service.IVerifyCodeService;
import com.metoo.nspm.entity.VerifyCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class VerifyCodeServiceImpl implements IVerifyCodeService {

    @Autowired
    private VerifyCodeMapper verifyCodeMapper;

    @Override
    public VerifyCode selectObjByMobile(String mobile) {
        return this.verifyCodeMapper.selectObjByMobile(mobile);
    }

    @Override
    public int update(String mobile, String code) {
        // 写入数据库- 写入redis
        VerifyCode verifyCode = this.verifyCodeMapper.selectObjByMobile(mobile);
        if(verifyCode == null){
            verifyCode = new VerifyCode();
            verifyCode.setCode(code);
            verifyCode.setMobile(mobile);
            verifyCode.setAddTime(new Date());
            return this.verifyCodeMapper.save(verifyCode);
        }else{
            verifyCode.setAddTime(new Date());
            verifyCode.setCode(code);
            verifyCode.setMobile(mobile);
            return this.verifyCodeMapper.update(verifyCode);
        }
    }

    @Override
    public int delete(String mobile) {
        return this.verifyCodeMapper.delete(mobile);
    }
}
