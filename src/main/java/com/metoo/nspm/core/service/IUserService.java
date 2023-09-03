package com.metoo.nspm.core.service;

import com.github.pagehelper.Page;
import com.metoo.nspm.core.dto.UserDto;
import com.metoo.nspm.entity.nspm.User;

import java.util.List;
import java.util.Map;

public interface IUserService {

    /**
     * 根据Username 查询一个User 对象
     * @param username
     * @return
     */
    User selectByName(String username);

    User selectObjById(Long id);

    List<User> selectObjByMap(Map params);

    Page<User> selectObjByConditionQuery(UserDto dto);

}
