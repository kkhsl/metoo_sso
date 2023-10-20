package com.metoo.nspm.core.mapper;

import com.metoo.nspm.entity.Department;

import java.util.List;

/**
 * @author HKK
 * @version 1.0
 * @date 2023-10-17 14:16
 */
public interface DepartmentMapper {

    List<Department> selectObjByParentDm(String dm);

    Department selectObjByDm(String dm);
}
