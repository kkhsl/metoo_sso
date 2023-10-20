package com.metoo.nspm.core.manager;

import com.github.pagehelper.Page;
import com.metoo.nspm.core.config.utils.ResponseUtil;
import com.metoo.nspm.core.config.utils.ShiroUserHolder;
import com.metoo.nspm.dto.ClientDTO;
import com.metoo.nspm.core.service.IClientService;
import com.metoo.nspm.core.service.IGroupService;
import com.metoo.nspm.core.utils.query.PageInfo;
import com.metoo.nspm.entity.Client;
import com.metoo.nspm.entity.Group;
import com.metoo.nspm.entity.User;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/admin")
@RestController
public class ClientManagerController {

    @Autowired
    private IClientService clientService;
    @Autowired
    private IGroupService groupService;

    @ApiOperation("客户端列表")
    @GetMapping("/userInfo")
    public Object user(){
        User user = ShiroUserHolder.currentUser();
        if(user.getGroupLevel() != null && !user.getGroupLevel().equals("")){
            Group group = groupService.getObjByLevel(user.getGroupLevel());
            user.setGroupLevel(group.getLevel());
            user.setGroupName(group.getBranchName());
        }
        return ResponseUtil.ok(user);
    }

    @ApiOperation("客户端列表")
    @PostMapping("/client")
    public Object clientList(@RequestBody ClientDTO dto){
        Page<Client> page = this.clientService.selectObjByConditionQuery(dto);
        if(page.getResult().size() > 0){
            return ResponseUtil.ok(new PageInfo<Client>(page));
        }
        return ResponseUtil.ok();
    }

    @ApiOperation("客户端列表")
    @GetMapping("/client2")
    public Object clientList2(@RequestBody(required = false) ClientDTO dto){
        if(dto == null){
            dto = new ClientDTO();
        }
        Page<Client> page = this.clientService.selectObjByConditionQuery(dto);
        if(page.getResult().size() > 0){
            return ResponseUtil.ok(new PageInfo<Client>(page));
        }
        return ResponseUtil.ok();
    }
}
