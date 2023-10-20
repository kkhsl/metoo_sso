package com.metoo.nspm.core.service;

import com.github.pagehelper.Page;
import com.metoo.nspm.dto.XsglXsDTO;
import com.metoo.nspm.entity.User;
import com.metoo.nspm.entity.XsglXs;

import java.util.List;

/**
 * @author HKK
 * @version 1.0
 * @date 2023-10-19 14:51
 */
public interface IXsglXsService {

    Page<XsglXs> selectObjByConditionQuery(XsglXsDTO dto);
}
