package com.metoo.nspm.core.service.impl;

import com.metoo.nspm.core.mapper.DepartmentMapper;
import com.metoo.nspm.core.service.IDepartmentService;
import com.metoo.nspm.entity.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author HKK
 * @version 1.0
 * @date 2023-10-17 14:15
 */
@Service
@Transactional
public class DepartmentServiceImpl implements IDepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public List<Department> selectObjByParentDm(String dm) {
        return this.departmentMapper.selectObjByParentDm(dm);
    }

    @Override
    public Department selectObjByDm(String dm) {
        return this.departmentMapper.selectObjByDm(dm);
    }
}
