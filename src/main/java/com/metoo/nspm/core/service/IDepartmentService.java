package com.metoo.nspm.core.service;

import com.metoo.nspm.entity.Department;

import java.util.List;

/**
 * @author HKK
 * @version 1.0
 * @date 2023-10-17 14:15
 */
public interface IDepartmentService {

    List<Department> selectObjByParentDm(String dm);

    Department selectObjByDm(String dm);
}
