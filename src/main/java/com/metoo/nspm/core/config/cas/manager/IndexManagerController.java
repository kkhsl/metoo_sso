package com.metoo.nspm.core.config.cas.manager;

import com.metoo.nspm.core.config.utils.ResponseUtil;
import com.metoo.nspm.core.config.utils.ShiroUserHolder;
import com.metoo.nspm.core.service.IIndexService;
import com.metoo.nspm.core.service.ISysConfigService;
import com.metoo.nspm.vo.MenuVo;
import com.metoo.nspm.entity.SysConfig;
import com.metoo.nspm.entity.User;
import io.swagger.annotations.ApiOperation;
import org.jasig.cas.client.authentication.AttributePrincipal;
import org.jasig.cas.client.validation.AssertionImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/admin")
@Controller
public class IndexManagerController {

    @GetMapping("index")
    @ResponseBody
    public ModelAndView root(HttpServletRequest request){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        HttpSession session = request.getSession();
//        session._const_cas_assertion_.principal.name

        AssertionImpl assertion= (AssertionImpl) session.getAttribute("_const_cas_assertion_");
        AttributePrincipal principal = assertion.getPrincipal();
        String username = principal.getName();

        Map<String, Object> attributes = principal.getAttributes();
        System.out.println("用户名：" + username);

        attributes.forEach((k,v)-> System.out.println(k+": "+v));

        return mv;
    }



    @Autowired
    private IIndexService indexService;
    @Autowired
    private ISysConfigService configService;

    @ApiOperation("系统导航")
    @RequestMapping("/index/nav")
    @ResponseBody
    public Object nav(){
        Map map = new HashMap();
        User user = ShiroUserHolder.currentUser();
        List<MenuVo> menuList = this.indexService.findMenu(user.getId());
        map.put("obj", menuList);
        SysConfig configs = this.configService.select();
        map.put("domain", configs.getDomain());
        return ResponseUtil.ok(map);
    }
}
