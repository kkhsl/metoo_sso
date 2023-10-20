package com.metoo.nspm.core.mapper;

import com.metoo.nspm.entity.GroupType;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author HKK
 * @version 1.0
 * @date 2023-10-11 10:23
 */
public interface GroupTypeMapper {

    GroupType selectObjById(@Param("id") Long id);

    List<GroupType> selectObjByParentId(@Param("parentId") Long parentId);

    List<GroupType> selectObjByMap(Map params);
}
