package com.metoo.nspm.core.mapper;

import com.metoo.nspm.entity.AuthCode;
import org.apache.ibatis.annotations.Mapper;

//@EnableMapRepositories
@Mapper
public interface AuthCodeMapper {

    AuthCode selectObjByCode(String code);

    AuthCode selectObjByToken(String token);

    int save(AuthCode instance);

    int update(AuthCode instance);

    int delete(Long id);
}
