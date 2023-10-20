package com.metoo.nspm.core.manager.tools;

import com.metoo.nspm.core.service.IGroupService;
import com.metoo.nspm.entity.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class GroupTools {

    @Autowired
    private IGroupService groupService;

    public List<Group> genericGroup(Group group){
        List<Group> groups = this.groupService.queryChild(group.getId());
        if(groups.size() > 0){
            for(Group child : groups){
                List<Group> branchList = genericGroup(child);
                if(branchList.size() > 0){
                    child.setBranchList(branchList);
                }
            }
            group.setBranchList(groups);
        }
        return groups;
    }

    public Set<Long> genericGroupId(Long id){
        Set<Long> ids = new HashSet();
        ids.add(id);
        List<Group> groups = this.groupService.queryChild(id);
        if(groups.size() > 0){
            for(Group child : groups){
                Set<Long> cids = genericGroupId(child.getId());
                ids.addAll(cids);
                ids.add(child.getId());
            }
        }
        return ids;
    }

    public List<Group> genericGroupParent(Group group){
        List<Group> groups = new ArrayList<>();
        groups.add(group);
        Group obj = this.groupService.selectObjById(group.getParentId());
        if(obj != null){
            groups.addAll(genericGroupParent(obj));
        }
        return groups;
    }

}
