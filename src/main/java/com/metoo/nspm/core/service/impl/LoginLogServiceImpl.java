package com.metoo.nspm.core.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.metoo.nspm.core.config.utils.ShiroUserHolder;
import com.metoo.nspm.core.mapper.LoginLogMapper;
import com.metoo.nspm.core.service.ILoginLogService;
import com.metoo.nspm.dto.LoginLogDTO;
import com.metoo.nspm.entity.LoginLog;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.net.InetAddress;
import java.util.Date;
import java.util.List;

/**
 * @author HKK
 * @version 1.0
 * @date 2023-10-20 9:51
 */
@Service
@Transactional
public class LoginLogServiceImpl implements ILoginLogService {

    @Resource
    private LoginLogMapper loginlogMapper;

    @Override
    public Page<LoginLog> selectObjByConditionQuery(LoginLogDTO dto) {
        if (dto == null) {
            dto = new LoginLogDTO();
        }
        Page<LoginLog> page = PageHelper.startPage(dto.getCurrentPage(), dto.getPageSize());
        this.loginlogMapper.selectObjByConditionQuery(dto);
        return page;
    }

    @Override
    public boolean save(LoginLog instance) {
        try {
            if(instance == null){
                instance = new LoginLog();
                instance.setLoginName(ShiroUserHolder.currentUser().getUsername());
                instance.setLoginTime(new Date());
                InetAddress inetAddress = InetAddress.getLocalHost();
                instance.setLoginIp(inetAddress.getHostAddress());
            }
            this.loginlogMapper.save(instance);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
