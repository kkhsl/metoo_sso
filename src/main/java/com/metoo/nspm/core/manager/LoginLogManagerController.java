package com.metoo.nspm.core.manager;

import com.github.pagehelper.Page;
import com.metoo.nspm.core.config.utils.ResponseUtil;
import com.metoo.nspm.core.service.ILoginLogService;
import com.metoo.nspm.core.utils.query.PageInfo;
import com.metoo.nspm.dto.LoginLogDTO;
import com.metoo.nspm.dto.UserDto;
import com.metoo.nspm.entity.LoginLog;
import com.metoo.nspm.entity.Role;
import com.metoo.nspm.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author HKK
 * @version 1.0
 * @date 2023-10-20 10:13
 */
@RequestMapping("/admin/login/log")
@RestController
public class LoginLogManagerController {

    @Autowired
    private ILoginLogService loginLogService;

    @PostMapping("/list")
    public Object list(@RequestBody LoginLogDTO dto){
        if (dto == null) {
            dto = new LoginLogDTO();
        }
        Page<LoginLog> page = this.loginLogService.selectObjByConditionQuery(dto);
        if (page.getResult().size() > 0) {
            return ResponseUtil.ok(new PageInfo<LoginLog>(page));
        }
        return ResponseUtil.ok();
    }

    @PostMapping("/save")
    public Object save(@RequestBody(required = false) LoginLog instance){
        boolean flag = this.loginLogService.save(instance);
        if(flag){
            return ResponseUtil.ok();
        }
        return ResponseUtil.error();
    }

}
