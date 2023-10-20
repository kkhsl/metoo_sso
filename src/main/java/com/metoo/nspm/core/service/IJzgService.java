package com.metoo.nspm.core.service;

import com.github.pagehelper.Page;
import com.metoo.nspm.dto.JzgDTO;
import com.metoo.nspm.dto.XsglXsDTO;
import com.metoo.nspm.entity.Jzg;

/**
 * @author HKK
 * @version 1.0
 * @date 2023-10-19 16:11
 */
public interface IJzgService {

    Page<Jzg> selectObjByConditionQuery(JzgDTO dto);
}
