package com.metoo.nspm.core.mapper;

import com.metoo.nspm.entity.GroupNode;

/**
 * @author HKK
 * @version 1.0
 * @date 2023-10-12 10:59
 */
public interface GroupNodeMapper {

    GroupNode selectObjByGroupId(Long groupId);

    int save(GroupNode instance);

    int update(GroupNode instance);

    int delete(Long id);
}
