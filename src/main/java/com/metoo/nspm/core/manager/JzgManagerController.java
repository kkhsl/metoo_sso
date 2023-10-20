package com.metoo.nspm.core.manager;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.metoo.nspm.core.config.utils.ResponseUtil;
import com.metoo.nspm.core.service.IJzgService;
import com.metoo.nspm.core.service.impl.JzgServiceImpl;
import com.metoo.nspm.dto.JzgDTO;
import com.metoo.nspm.entity.Jzg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author HKK
 * @version 1.0
 * @date 2023-10-19 16:33
 */
@RequestMapping("/admin/jzggl/jzg")
@RestController
public class JzgManagerController {

    @Autowired
    private IJzgService jzgService;

    @PostMapping("/list")
    private Object list(@RequestBody JzgDTO dto){
        Page<Jzg> page = this.jzgService.selectObjByConditionQuery(dto);
        if(page.getResult().size() > 0){
            return ResponseUtil.ok(new PageInfo<Jzg>(page));
        }
        return ResponseUtil.ok();
    }
}
