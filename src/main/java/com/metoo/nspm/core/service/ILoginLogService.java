package com.metoo.nspm.core.service;

import com.github.pagehelper.Page;
import com.metoo.nspm.dto.LoginLogDTO;
import com.metoo.nspm.entity.LoginLog;

import java.util.List;

/**
 * @author HKK
 * @version 1.0
 * @date 2023-10-20 9:48
 */
public interface ILoginLogService {

    Page<LoginLog> selectObjByConditionQuery(LoginLogDTO dto);

    boolean save(LoginLog instance);
}
