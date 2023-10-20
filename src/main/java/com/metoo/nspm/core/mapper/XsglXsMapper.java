package com.metoo.nspm.core.mapper;

import com.metoo.nspm.dto.XsglXsDTO;
import com.metoo.nspm.entity.XsglXs;

import java.util.List;

/**
 * @author HKK
 * @version 1.0
 * @date 2023-10-19 14:55
 */
public interface XsglXsMapper {

    List<XsglXs> selectObjByConditionQuery(XsglXsDTO dto);
}
