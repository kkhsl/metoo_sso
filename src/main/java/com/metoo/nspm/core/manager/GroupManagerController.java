package com.metoo.nspm.core.manager;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.metoo.nspm.core.config.utils.ResponseUtil;
import com.metoo.nspm.core.config.utils.ShiroUserHolder;
import com.metoo.nspm.core.manager.tools.GroupTools;
import com.metoo.nspm.core.manager.tools.ReflectionUtils;
import com.metoo.nspm.core.service.IGroupNodeService;
import com.metoo.nspm.core.service.IGroupService;
import com.metoo.nspm.core.service.IGroupTypeService;
import com.metoo.nspm.core.service.IUserService;
import com.metoo.nspm.dto.GroupDto;
import com.metoo.nspm.dto.GroupNodeDTO;
import com.metoo.nspm.entity.Group;
import com.metoo.nspm.entity.GroupNode;
import com.metoo.nspm.entity.GroupType;
import com.metoo.nspm.entity.User;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RequestMapping("/admin/group")
@RestController
public class GroupManagerController {

    @Autowired
    private IGroupService groupService;
    @Autowired
    private GroupTools groupTools;
    @Autowired
    private IUserService userService;
    @Autowired
    private IGroupTypeService groupTypeService;
    @Autowired
    private IGroupNodeService groupNodeService;

    @RequestMapping("/list")
    @ResponseBody
    public Object list(@RequestBody(required = false) GroupDto dto){
        User currentUser = ShiroUserHolder.currentUser();
        User user = this.userService.findByUserName(currentUser.getUsername());
        Group parent = this.groupService.selectObjById(user.getGroupId());
        if(parent != null){
            List<Group> branchList = new ArrayList<Group>();
            if(this.groupTools.genericGroup(parent).size() > 0){
                this.groupTools.genericGroup(parent);
            }
            branchList.add(parent);
            return ResponseUtil.ok(branchList);
        }
        return ResponseUtil.ok();
    }

    @GetMapping("/detail")
    public Object detail(@RequestParam(required = false) Long id){
        Group group = this.groupService.selectObjById(id);
        if(group == null){
            User currentUser = ShiroUserHolder.currentUser();
            User user = this.userService.findByUserName(currentUser.getUsername());
            group = this.groupService.selectObjById(user.getGroupId());
        }
        Map result = new HashMap();
        // 遍历出上级
        List<Group> groups = this.groupTools.genericGroupParent(group);
        Collections.reverse(groups);
        StringBuilder sb = new StringBuilder();
        if(groups.size() > 0){
            int size = groups.size();
            if (size > 0) {
                size--;
                for(int i=0; i<size; i++)  {
                    sb.append(groups.get(i).getBranchName()).append("/");
                }
                sb.append(groups.get(size).getBranchName());
            }
        }

        result.put("navname", sb.toString());
        GroupType groupType = this.groupTypeService.selectObjById(group.getGroupTypeId());
        if(groupType != null){
            group.setGroupTypeId(groupType.getId());
            group.setGroupTypeName(groupType.getName());
        }
        result.put("group", group);

        // 组织架构-领导者
        GroupNode groupNode = this.groupNodeService.selectObjByGroupId(group.getId());
        Map manager = new HashMap();
        if(groupNode != null){
            result.put("groupNode", groupNode);
            manager.put("departmentManager", "");
            if(groupNode.getDepartmentManager() != null && !groupNode.getDepartmentManager().equals("")){
                List<Integer> departmentManager = JSONObject.parseObject(groupNode.getDepartmentManager(), List.class);

                if(departmentManager.size() > 0){
                    StringBuilder sb1 = new StringBuilder();
                    int size = departmentManager.size();
                    if (size > 0) {
                        size--;
                        for(int i=0; i < size; i++)  {
                            User user = this.userService.selectObjById(departmentManager.get(i).longValue());
                            sb1.append(user.getUsername()).append(",");
                        }
                        User user = this.userService.selectObjById(departmentManager.get(size).longValue());
                        sb1.append(user.getUsername());
                    }
                    manager.put("departmentManager", sb1.toString());
                }
            }
            manager.put("classTearch", "");
            if(groupNode.getClassTearch() != null && !groupNode.getClassTearch().equals("")){
                List<Integer> classTearch = JSONObject.parseObject(groupNode.getClassTearch(), List.class);

                if(classTearch.size() > 0){
                    StringBuilder sb1 = new StringBuilder();
                    int size = classTearch.size();
                    if (size > 0) {
                        size--;
                        for(int i=0; i < size; i++)  {
                            User user = this.userService.selectObjById(classTearch.get(i).longValue());
                            sb1.append(user.getUsername()).append(",");
                        }
                        User user = this.userService.selectObjById(classTearch.get(size).longValue());
                        sb1.append(user.getUsername());
                    }
                    manager.put("classTearch", sb1.toString());
                }
            }
            manager.put("tearch", "");
            if(groupNode.getTeacher() != null && !groupNode.getTeacher().equals("")){
                List<Integer> tearch = JSONObject.parseObject(groupNode.getTeacher(), List.class);

                if(tearch.size() > 0){
                    StringBuilder sb1 = new StringBuilder();
                    int size = tearch.size();
                    if (size > 0) {
                        size--;
                        for(int i=0; i < size; i++)  {
                            User user = this.userService.selectObjById(tearch.get(i).longValue());
                            sb1.append(user.getUsername()).append(",");
                        }
                        User user = this.userService.selectObjById(tearch.get(size).longValue());
                        sb1.append(user.getUsername());
                    }
                    manager.put("tearch", sb1.toString());
                }
            }
            manager.put("monitor", "");
            if(groupNode.getMonitor() != null && !groupNode.getMonitor().equals("")){
                List<Integer> monitor = JSONObject.parseObject(groupNode.getMonitor(), List.class);

                if(monitor.size() > 0){
                    StringBuilder sb1 = new StringBuilder();
                    int size = monitor.size();
                    if (size > 0) {
                        size--;
                        for(int i=0; i < size; i++)  {
                            User user = this.userService.selectObjById(monitor.get(i).longValue());
                            sb1.append(user.getUsername()).append(",");
                        }
                        User user = this.userService.selectObjById(monitor.get(size).longValue());
                        sb1.append(user.getUsername());
                    }
                    manager.put("monitor", sb1.toString());
                }
            }
            manager.put("president", "");
            if(groupNode.getPresident() != null && !groupNode.getPresident().equals("")){
                List<Integer> president = JSONObject.parseObject(groupNode.getPresident(), List.class);

                if(president.size() > 0){
                    StringBuilder sb1 = new StringBuilder();
                    int size = president.size();
                    if (size > 0) {
                        size--;
                        for(int i=0; i < size; i++)  {
                            User user = this.userService.selectObjById(president.get(i).longValue());
                            sb1.append(user.getUsername()).append(",");
                        }
                        User user = this.userService.selectObjById(president.get(size).longValue());
                        sb1.append(user.getUsername());
                    }
                    manager.put("president", sb1.toString());
                }
            }
            manager.put("gradGroupLeader", "");
            if(groupNode.getGradGroupLeader() != null && !groupNode.getGradGroupLeader().equals("")){
                List<Integer> gradGroupLeader = JSONObject.parseObject(groupNode.getGradGroupLeader(), List.class);

                if(gradGroupLeader.size() > 0){
                    StringBuilder sb1 = new StringBuilder();
                    int size = gradGroupLeader.size();
                    if (size > 0) {
                        size--;
                        for(int i=0; i < size; i++)  {
                            User user = this.userService.selectObjById(gradGroupLeader.get(i).longValue());
                            sb1.append(user.getUsername()).append(",");
                        }
                        User user = this.userService.selectObjById(gradGroupLeader.get(size).longValue());
                        sb1.append(user.getUsername());
                    }
                    manager.put("gradGroupLeader", sb1.toString());
                }
            }

            manager.put("groupLeader", "");
            if(groupNode.getGroupLeader() != null && !groupNode.getGroupLeader().equals("")){
                List<Integer> groupLeader = JSONObject.parseObject(groupNode.getGroupLeader(), List.class);
                if(groupLeader.size() > 0){
                    StringBuilder sb1 = new StringBuilder();
                    int size = groupLeader.size();
                    if (size > 0) {
                        size--;
                        for(int i=0; i < size; i++)  {
                            User user = this.userService.selectObjById(groupLeader.get(i).longValue());
                            sb1.append(user.getUsername()).append(",");
                        }
                        User user = this.userService.selectObjById(groupLeader.get(size).longValue());
                        sb1.append(user.getUsername());
                    }
                    manager.put("groupLeader", sb1.toString());
                }
            }
        }
        result.put("manager", manager);

        // 查询下级
        List<Group> child = this.groupService.queryChild(group.getId());
        result.put("child", child);

        return ResponseUtil.ok(result);
    }


    @ApiOperation("部门管理者-添加")
    @GetMapping("/manager/add")
    public Object managerAdd(@RequestParam Long id,
                             @RequestParam String propertyName) throws NoSuchFieldException, IllegalAccessException {
        GroupNode groupNode = this.groupNodeService.selectObjByGroupId(id);
        List list = new ArrayList();
        if(groupNode != null){
            Object propertyValue = ReflectionUtils.getPropertyValue(groupNode, propertyName);
            List<Integer> groupLeader = JSONObject.parseObject(String.valueOf(propertyValue), List.class);
            if(groupLeader.size() > 0){
                for (Integer integer : groupLeader) {
                    User user = this.userService.selectObjById(groupLeader.get(integer).longValue());
                    list.add(user);
                }
            }
        }
        return ResponseUtil.ok(list);
    }

    public static void main(String[] args) {
        GroupNode person = new GroupNode();

        try {
            String propertyName = "departmentManager";
            Object propertyValue = ReflectionUtils.getPropertyValue(person, propertyName);
            System.out.println(propertyName + ": " + propertyValue);


        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }



    @ApiOperation("下级部门")
    @GetMapping("/tree/node")
    public Object treeNode(@RequestParam(required = false) Long id){
        Group group = this.groupService.selectObjById(id);
        if(group == null){
            User currentUser = ShiroUserHolder.currentUser();
            User user = this.userService.findByUserName(currentUser.getUsername());
            group = this.groupService.selectObjById(user.getGroupId());
        }
        Map map = new HashMap();
        // 查询下级
        List<Group> groups = this.groupService.queryChild(group.getId());
        map.put("groups", group);
        return ResponseUtil.ok(groups);
    }

    @GetMapping("/add")
    public Object add(@RequestParam(required = false) Long id){
        Group group = this.groupService.selectObjById(id);
        if(group == null){
            return ResponseUtil.badArgumentValue();
        }

//        GroupType groupType = this.groupTypeService.selectObjById(group.getGroupTypeId());
//        if(groupType != null){
//            if(groupType.getType() == -1){
//
//            }
//        }

        // 查询下级
        List<GroupType> groupTypes = this.groupTypeService.selectObjByParentId(group.getGroupTypeId());
        if(groupTypes.size() <= 0){
            return ResponseUtil.sysInfo("不能增加子节点");
        }
        return ResponseUtil.ok(groupTypes);
    }

    @GetMapping("/update")
    public Object update(@RequestParam(required = false) Long id){
        Group group = this.groupService.selectObjById(id);
        if(group == null){
            return ResponseUtil.badArgumentValue();
        }

        GroupType groupType = this.groupTypeService.selectObjById(group.getGroupTypeId());
        if(groupType != null){
            group.setGroupTypeName(groupType.getName());
            group.setGroupTypeId(groupType.getId());
        }
        return ResponseUtil.ok(group);
    }


    @ApiOperation("选择")
    @GetMapping("/selector")
    public Object selector(@RequestParam Integer type,
                           @RequestParam Long id,
                           @RequestParam String propertyName){
        if(type == null || type.equals("")){
            return ResponseUtil.badArgument("参数必填");
        }
        if(type <= 0){
            return ResponseUtil.badArgument("参数错误");
        }
        Map map = new HashMap();
        List<Group> branchList = new ArrayList<Group>();
        Map params = new HashMap();
        params.put("type", type);
        List<GroupType> groupTypes = this.groupTypeService.selectObjByMap(params);
        if(groupTypes.size() > 0){
            GroupType groupType = groupTypes.get(0);
            params.clear();
            params.put("groupTypeId", groupType.getId());
            List<Group> groups = this.groupService.selectObjByMap(params);
            if(groups.size() > 0){
                Group group = groups.get(0);
                if(group != null){
                    if(this.groupTools.genericGroup(group).size() > 0){
                        this.groupTools.genericGroup(group);
                    }
                    branchList.add(group);

                }
            }

            GroupNode groupNode = this.groupNodeService.selectObjByGroupId(id);
            List list = new ArrayList();
            if(groupNode != null){
                Object propertyValue = null;
                try {
                    propertyValue = ReflectionUtils.getPropertyValue(groupNode, propertyName);
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                if(propertyValue != null){
                    List<Integer> groupLeader = JSONObject.parseObject(String.valueOf(propertyValue), List.class);
                    if(groupLeader.size() > 0){
                        for (Integer integer : groupLeader) {
                            User user = this.userService.selectObjById(integer.longValue());
                            list.add(user);
                        }
                    }
                }
            }

            map.put("user", list);
            map.put("groups", branchList);
            return ResponseUtil.ok(map);
        }
        return ResponseUtil.badArgument("参数错误");
    }

    @PostMapping("/save")
    public Object save(@RequestBody GroupDto dto){
        if(dto.getGroupTypeId() != null){
            GroupType groupType = this.groupTypeService.selectObjById(dto.getGroupTypeId());
            if(groupType != null){
                if(groupType.getType() > 0){
                    Group group = this.groupService.selectObjByName(groupType.getName());
                    if(group != null){
                        return ResponseUtil.sysInfo("同一层不能存在同名节点");
                    }else{
                        dto.setBranchName(groupType.getName());
                        Map params = new HashMap();
                        params.put("groupTypeId", groupType.getParentId());
                        List<Group> groups = this.groupService.selectObjByMap(params);
                        if(groups.size() > 0){
                            dto.setParentId(groups.get(0).getId());
                        }
                    }
                }
            }
        }

        if(dto.getParentId() == null || dto.getParentId().equals("")){
            return ResponseUtil.badArgument("请输入上级分组");
        }
        if(dto.getParentId() != null){
            Group groupParent = this.groupService.selectObjById(dto.getParentId());
            if(groupParent == null){
                return ResponseUtil.badArgument("上级分组不存在");
            }
            // 同一层不能存在同名节点 return ResponseUtil.sysInfo("同一层不能存在同名节点");
            List<Group> groups = this.groupService.queryChild(dto.getParentId());
            if(groups.size() > 0){
                List<Group> groups1 = groups.stream().filter(e -> e.getBranchName().equals(dto.getBranchName())).collect(Collectors.toList());
                if(groups1.size() > 0){
                    return ResponseUtil.sysInfo("同一层不能存在同名节点");
                }
            }
        }

        if(dto.getBranchName() != null){
            return ResponseUtil.ok(this.groupService.save(dto));
        }
        return ResponseUtil.badArgument("请输入分组名称");
    }

    @ApiOperation("组织架构-管理者-保存")
    @PostMapping("/node/save")
    public Object save2(@RequestBody GroupNodeDTO groupNodeDTO){
        GroupNode groupNode = this.groupNodeService.selectObjByGroupId(groupNodeDTO.getGroupId());
        if(groupNode == null){
            groupNode = new GroupNode();
        }
        groupNode.setGroupId(groupNodeDTO.getGroupId());
        if(groupNodeDTO.getDepartmentManager() != null){
            groupNode.setDepartmentManager(JSONObject.toJSONString(groupNodeDTO.getDepartmentManager()));
        }
        if(groupNodeDTO.getClassTearch() != null){
            groupNode.setClassTearch(JSONObject.toJSONString(groupNodeDTO.getClassTearch()));
        }
        if(groupNodeDTO.getTeacher() != null){
            groupNode.setTeacher(JSONObject.toJSONString(groupNodeDTO.getTeacher()));
        }
        if(groupNodeDTO.getMonitor() != null){
            groupNode.setMonitor(JSONObject.toJSONString(groupNodeDTO.getMonitor()));
        }
        if(groupNodeDTO.getPresident() != null){
            groupNode.setPresident(JSONObject.toJSONString(groupNodeDTO.getPresident()));
        }
        if(groupNodeDTO.getGradGroupLeader() != null){
            groupNode.setGradGroupLeader(JSONObject.toJSONString(groupNodeDTO.getGradGroupLeader()));
        }
        if(groupNodeDTO.getGroupLeader() != null){
            groupNode.setGroupLeader(JSONObject.toJSONString(groupNodeDTO.getGroupLeader()));
        }
        boolean flag = this.groupNodeService.save(groupNode);
        if(flag){
            return ResponseUtil.ok();
        }
        return ResponseUtil.error();
    }

    @RequestMapping("/del")
    public Object del(@RequestBody GroupDto dto){
        Group group = this.groupService.selectObjById(dto.getId());
        if(group == null){
            return ResponseUtil.badArgument();
        }
        Map params = new HashMap();
        params.put("groupId", group.getId());
        List<User> users = this.userService.selectObjByMap(params);
        if(users.size() > 0){
            return ResponseUtil.badArgument("请先删除部门中的用户");
        }
        if (this.delGroup(group.getId())){
            return ResponseUtil.ok();
        }
        return ResponseUtil.error();
    }

    public boolean delGroup(Long id){
        try {
            Group obj = this.groupService.selectObjById(id);
            if(obj != null){
                List<String> users = this.userService.getObjByLevel(obj.getLevel());
                if(users.size() > 0){
                    this.userService.deleteByLevel(obj.getLevel());
                }
                this.groupService.del(obj.getId());
                List<Group> groupList = this.groupService.queryChild(id);
                if (groupList.size() > 0){
                    for (Group group:groupList){
                        this.delGroup(group.getId());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @GetMapping("/verify")
    public Object del(String id){
        Group group = this.groupService.selectObjById(Long.parseLong(id));
        if(group != null){
            List<String> users = this.userService.getObjByLevel(group.getLevel());
            if(users.size() > 0){
                User user = ShiroUserHolder.currentUser();
                if(users.contains(user.getUsername())){
                    return ResponseUtil.ok(2);
                }
                return ResponseUtil.ok(1);
            }
            List<Group> groupList = this.groupService.queryChild(Long.parseLong(id));
            if(groupList.size() > 0){
                return ResponseUtil.ok(1);
            }
            return ResponseUtil.ok(0);
        }
        return ResponseUtil.badArgument("分组不存在");
    }

    @RequestMapping("/parent")
    @ResponseBody
    public Object queryParent(@RequestBody(required = false) GroupDto dto){
        if(dto != null){
            List<Group> parent = this.groupService.queryChild(dto.getParentId());
            return ResponseUtil.ok(parent);
        }else{
            List<Group> parent = this.groupService.queryChild(null);
            return ResponseUtil.ok(parent);
        }
    }

}
