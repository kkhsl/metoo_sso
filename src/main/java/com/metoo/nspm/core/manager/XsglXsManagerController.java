package com.metoo.nspm.core.manager;

import com.github.pagehelper.Page;
import com.metoo.nspm.core.config.utils.ResponseUtil;
import com.metoo.nspm.core.service.IXsglXsService;
import com.metoo.nspm.core.utils.query.PageInfo;
import com.metoo.nspm.dto.XsglXsDTO;
import com.metoo.nspm.entity.Role;
import com.metoo.nspm.entity.XsglXs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author HKK
 * @version 1.0
 * @date 2023-10-19 15:08
 */
@RequestMapping("/admin/xsgl/xs")
@RestController
public class XsglXsManagerController {

    @Autowired
    private IXsglXsService xsglXsService;

    @PostMapping("/list")
    public Object list(@RequestBody XsglXsDTO dto){
        Page<XsglXs> page = this.xsglXsService.selectObjByConditionQuery(dto);
        if(page.getResult().size() > 0){
            return ResponseUtil.ok(new PageInfo<XsglXs>(page));
        }
        return ResponseUtil.ok();
    }
}
