package com.metoo.nspm.core.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.metoo.nspm.core.config.utils.ShiroUserHolder;
import com.metoo.nspm.core.service.*;
import com.metoo.nspm.dto.UserDto;
import com.metoo.nspm.core.mapper.UserMapper;
import com.metoo.nspm.entity.Group;
import com.metoo.nspm.entity.Role;
import com.metoo.nspm.entity.User;
import com.metoo.nspm.entity.UserRole;
import com.metoo.nspm.vo.UserVo;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private IUserRoleService userRoleService;
    @Autowired
    private IRoleService roleService;
    @Autowired
    private IGroupService groupService;

    @Override
    public User selectByName(String username) {
        return this.userMapper.selectByName(username);
    }

    @Override
    public User selectObjByMobile(String mobile) {
        return this.userMapper.selectObjByMobile(mobile);
    }

    @Override
    public User selectObjById(Long id) {
        return this.userMapper.selectObjById(id);
    }

    @Override
    public List<User> selectObjByMap(Map params) {

        return this.userMapper.selectObjByMap(params);
    }

    @Override
    public Page<User> selectObjByConditionQuery(UserDto dto) {
        if (dto == null) {
            dto = new UserDto();
        }
        Page<User> page = PageHelper.startPage(dto.getCurrentPage(), dto.getPageSize());
        this.userMapper.selectObjByConditionQuery(dto);
        return page;
    }

    @Override
    public User findByUserName(String username) {
        return this.userMapper.findByUserName(username);
    }

    @Override
    public User findRolesByUserName(String username) {
        return null;
    }

    @Override
    public UserVo findUserUpdate(Long id) {
        return this.userMapper.findUserUpdate(id);
    }

    @Override
    public User findObjById(Long id) {
        return this.userMapper.selectPrimaryKey(id);
    }

    @Override
    public Page<UserVo> getObjsByLevel(UserDto dto) {
        if(dto.getGroupLevel() == null || dto.getGroupLevel().equals("")){
            User currentUser = ShiroUserHolder.currentUser();
            User user = this.findByUserName(currentUser.getUsername());
            dto.setGroupLevel(user.getGroupLevel());
        }
        Page<UserVo> page = PageHelper.startPage(dto.getCurrentPage(), dto.getPageSize());
        this.userMapper.getObjsByLevel(dto.getGroupLevel());
        return page;
    }

    @Override
    public List<String> getObjByLevel(String level) {
        Group group = this.groupService.getObjByLevel(level);
        if(group == null){
            User currentUser = ShiroUserHolder.currentUser();
            User user = this.findByUserName(currentUser.getUsername());
            group = this.groupService.getObjByLevel(user.getGroupLevel());
        }
        if(group != null) {
            List<Group> groups = this.groupService.queryChild(group.getId());
            groups.add(group);
            List<String> users = this.userMapper.getObjByLevel(groups);
            return users;
        }

        return null;
    }

    @Override
    public Page<UserVo> query(UserDto dto) {
        Page<UserVo> page = PageHelper.startPage(dto.getCurrentPage(), dto.getPageSize());
        this.userMapper.query(dto);
        return page;
    }


    @Override
    public boolean save(UserDto dto) {
        User user = null;
        if(dto.getId() == null){
            user = new User();
            dto.setAddTime(new Date());
        }else{
            user = this.userMapper.selectPrimaryKey(dto.getId());
        }
        BeanUtils.copyProperties(dto, user);
        // 查询组信息
        Group group = this.groupService.selectObjById(dto.getGroupId());
        if(group != null){
            user.setGroupName(group.getBranchName());
            user.setGroupLevel(group.getLevel());
        }else{// 未设置组时，默认为所属组与当前用户相同
            User currentUser = ShiroUserHolder.currentUser();
            currentUser = this.userMapper.findByUserName(currentUser.getUsername());
            user.setGroupId(currentUser.getGroupId());
        }
        if(dto.getId() == null){
            try {
                this.userMapper.insert(user);
                String roleName = "";
                // 批量添加用户角色信息
                if(dto.getRole_id() != null && dto.getRole_id().length > 0){
                    List<Integer> idList = Arrays.asList(dto.getRole_id());
                    List<Role> roleList = this.roleService.findRoleByIdList(idList);
                    List<UserRole> userRoles = new ArrayList<UserRole>();
                    for(Role role : roleList){
                        UserRole userRole = new UserRole();
                        userRole.setUser_id(user.getId());
                        userRole.setRole_id(role.getId());
                        userRoles.add(userRole);
                        roleName += role.getName() + ",";
                    }
                    roleName = roleName.substring(0,roleName.lastIndexOf(","));
                    this.userRoleService.batchAddUserRole(userRoles);
                }
                try {
                    user.setUserRole(roleName);
                    this.userMapper.update(user);
                    return true;
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }else{
            try {
                String roleName = "";

                // 批量添加用户角色信息
                if(dto.getRole_id() != null && dto.getRole_id().length > 0){
                    // 清除用户角色信息
                    this.userRoleService.deleteUserByRoleId(user.getId());
                    List<Integer> idList = Arrays.asList(dto.getRole_id());
                    List<Role> roleList = this.roleService.findRoleByIdList(idList);
                    List<UserRole> userRoles = new ArrayList<UserRole>();
                    for(Role role : roleList){
                        UserRole userRole = new UserRole();
                        userRole.setUser_id(user.getId());
                        userRole.setRole_id(role.getId());
                        userRoles.add(userRole);
                        roleName += role.getName() + ",";
                    }
                    roleName = roleName.substring(0, roleName.lastIndexOf(","));
                    this.userRoleService.batchAddUserRole(userRoles);
                }
                user.setUserRole(roleName);
                this.userMapper.update(user);
                User currentUser = ShiroUserHolder.currentUser();


                // 第一种方式 强制退出当前帐号
                // 如果修改的是当前已登录用户信息则退出当前帐号
                if(dto.isFlag() && currentUser.getId().equals(user.getId())){
                    SecurityUtils.getSubject().logout();
                    // 修改身份信息后，动态更改Subject的用户属性
                   /* Subject subject = SecurityUtils.getSubject();
                    String username = (String) subject.getPrincipal();
                    User userInfo = this.userMapper.findByUserName(username);// 查询指定属性，封装到Subject内
                    PrincipalCollection newPrincipalCollection =
                            new SimplePrincipalCollection(userInfo, userInfo.getUsername());
                    subject.runAs(newPrincipalCollection);*/
                }

                //第二种防止 强制退出被修改用户

                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
    }

    @Override
    public boolean update(User user) {
        try {
            this.userMapper.update(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(User user) {
        // 清除用户角色
        try {
            this.userRoleService.deleteUserByRoleId(user.getId());
            this.userMapper.delete(user.getId());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteByLevel(String level) {
        return this.userMapper.deleteByLevel(level);
    }

    @Override
    public boolean allocation(List<User> list){
        try {
            this.userMapper.allocation(list);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<User> findObjByIds(Long[] ids) {
        return this.userMapper.findObjByIds(ids);
    }



}
