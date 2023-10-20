package com.metoo.nspm.core.service;

import com.metoo.nspm.entity.GroupNode;

/**
 * @author HKK
 * @version 1.0
 * @date 2023-10-12 10:57
 */
public interface IGroupNodeService {

    GroupNode selectObjByGroupId(Long groupId);

    boolean save(GroupNode instance);

    boolean update(GroupNode instance);

    int delete(Long id);
}
