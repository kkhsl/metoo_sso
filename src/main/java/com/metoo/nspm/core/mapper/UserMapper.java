package com.metoo.nspm.core.mapper;

import com.github.pagehelper.Page;
import com.metoo.nspm.core.dto.UserDto;
import com.metoo.nspm.entity.nspm.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {

    /**
     * 根据Username 查询一个User 对象
     * @param username
     * @return
     */
    User selectByName(String username);

    User selectObjById(Long id);

    List<User> selectObjByMap(Map params);

    List<User> selectObjByConditionQuery(UserDto dto);
}
