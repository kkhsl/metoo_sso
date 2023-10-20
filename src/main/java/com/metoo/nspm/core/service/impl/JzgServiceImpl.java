package com.metoo.nspm.core.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.metoo.nspm.core.mapper.JzgMapper;
import com.metoo.nspm.core.service.IJzgService;
import com.metoo.nspm.dto.JzgDTO;
import com.metoo.nspm.dto.XsglXsDTO;
import com.metoo.nspm.entity.Jzg;
import com.metoo.nspm.entity.XsglXs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author HKK
 * @version 1.0
 * @date 2023-10-19 16:13
 */
@Service
@Transactional
public class JzgServiceImpl implements IJzgService {

    @Autowired
    private JzgMapper jzgMapper;

    @Override
    public Page<Jzg> selectObjByConditionQuery(JzgDTO dto) {
        if (dto == null) {
            dto = new JzgDTO();
        }
        Page<Jzg> page = PageHelper.startPage(dto.getCurrentPage(), dto.getPageSize());
        this.jzgMapper.selectObjByConditionQuery(dto);
        return page;
    }

}
