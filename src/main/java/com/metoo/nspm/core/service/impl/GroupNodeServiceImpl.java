package com.metoo.nspm.core.service.impl;

import com.metoo.nspm.core.mapper.GroupNodeMapper;
import com.metoo.nspm.core.service.IGroupNodeService;
import com.metoo.nspm.entity.GroupNode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author HKK
 * @version 1.0
 * @date 2023-10-12 10:59
 */
@Service
@Transactional
public class GroupNodeServiceImpl implements IGroupNodeService {

    @Resource
    private GroupNodeMapper groupNodeMapper;

    @Override
    public GroupNode selectObjByGroupId(Long groupId) {
        return this.groupNodeMapper.selectObjByGroupId(groupId);
    }

    @Override
    public boolean save(GroupNode instance) {
        if(instance.getId() == null || instance.getId().equals("")){
            try {
                this.groupNodeMapper.save(instance);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }else{
            try {
                this.groupNodeMapper.update(instance);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
    }

    @Override
    public boolean update(GroupNode instance) {
        try {
            this.groupNodeMapper.update(instance);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public int delete(Long id) {
        return this.groupNodeMapper.delete(id);
    }
}
