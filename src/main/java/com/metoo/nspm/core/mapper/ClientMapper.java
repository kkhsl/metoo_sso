package com.metoo.nspm.core.mapper;

import com.metoo.nspm.dto.ClientDTO;
import com.metoo.nspm.entity.Client;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClientMapper {

    List<Client> selectObjByConditionQuery(ClientDTO dto);
}
