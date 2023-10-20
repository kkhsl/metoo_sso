package com.metoo.nspm.core.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.metoo.nspm.core.mapper.XsglXsMapper;
import com.metoo.nspm.core.service.IXsglXsService;
import com.metoo.nspm.dto.UserDto;
import com.metoo.nspm.dto.XsglXsDTO;
import com.metoo.nspm.entity.User;
import com.metoo.nspm.entity.XsglXs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author HKK
 * @version 1.0
 * @date 2023-10-19 14:54
 */
@Service
@Transactional
public class XsglXsServiceImpl implements IXsglXsService {

    @Autowired
    private XsglXsMapper xsglXsMapper;

    @Override
    public Page<XsglXs> selectObjByConditionQuery(XsglXsDTO dto) {
        if (dto == null) {
            dto = new XsglXsDTO();
        }
        Page<XsglXs> page = PageHelper.startPage(dto.getCurrentPage(), dto.getPageSize());
        this.xsglXsMapper.selectObjByConditionQuery(dto);
        return page;
    }
}
