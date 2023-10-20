package com.metoo.nspm.core.service;

import com.metoo.nspm.entity.GroupType;

import java.util.List;
import java.util.Map;

/**
 * @author HKK
 * @version 1.0
 * @date 2023-10-11 10:20
 */
public interface IGroupTypeService {

    GroupType selectObjById(Long id);

    List<GroupType> selectObjByParentId(Long parentId);


    List<GroupType> selectObjByMap(Map params);

}
