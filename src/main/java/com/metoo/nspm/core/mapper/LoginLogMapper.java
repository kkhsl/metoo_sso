package com.metoo.nspm.core.mapper;

import com.metoo.nspm.dto.LoginLogDTO;
import com.metoo.nspm.entity.LoginLog;

import java.util.List;

/**
 * @author HKK
 * @version 1.0
 * @date 2023-10-20 9:51
 */
public interface LoginLogMapper {

    List<LoginLog> selectObjByConditionQuery(LoginLogDTO dto);

    int save(LoginLog instance);
}
