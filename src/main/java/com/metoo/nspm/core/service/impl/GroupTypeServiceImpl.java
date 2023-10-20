package com.metoo.nspm.core.service.impl;

import com.metoo.nspm.core.mapper.GroupTypeMapper;
import com.metoo.nspm.core.service.IGroupTypeService;
import com.metoo.nspm.entity.GroupType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author HKK
 * @version 1.0
 * @date 2023-10-11 10:21
 */
@Service
@Transactional
public class GroupTypeServiceImpl implements IGroupTypeService {

    @Autowired
    private GroupTypeMapper groupTypeMapper;

    @Override
    public GroupType selectObjById(Long id) {
        return this.groupTypeMapper.selectObjById(id);
    }

    @Override
    public List<GroupType> selectObjByParentId(Long parentId) {
        return this.groupTypeMapper.selectObjByParentId(parentId);
    }

    @Override
    public List<GroupType> selectObjByMap(Map params) {
        return this.groupTypeMapper.selectObjByMap(params);
    }
}
