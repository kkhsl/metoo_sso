package com.metoo.nspm.core.mapper;

import com.github.pagehelper.Page;
import com.metoo.nspm.dto.JzgDTO;
import com.metoo.nspm.entity.Jzg;

/**
 * @author HKK
 * @version 1.0
 * @date 2023-10-19 16:13
 */
public interface JzgMapper {

    Page<Jzg> selectObjByConditionQuery(JzgDTO dto);
}
