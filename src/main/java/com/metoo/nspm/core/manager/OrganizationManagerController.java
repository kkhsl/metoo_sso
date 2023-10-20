package com.metoo.nspm.core.manager;

import com.metoo.nspm.core.config.utils.ResponseUtil;
import com.metoo.nspm.core.service.*;
import com.metoo.nspm.entity.*;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @author HKK
 * @version 1.0
 * @date 2023-10-16 15:30
 */
@RequestMapping("/admin/organization")
@RestController
public class OrganizationManagerController {

    @Autowired
    private IOrganizationService organizationService;
    @Autowired
    private ISpecialtyService specialtyService;
    @Autowired
    private IDepartmentService departmentService;
    @Autowired
    private IBmlxService bmlxService;
    @Autowired
    private IBmGlryService bmGlryService;

//    @GetMapping("/query")
//    public Object ggxxOrganizationQuery(){
//        return this.organizationService.ggxxOrganizationQuery();
//    }

    @GetMapping("/query")
    public Object ggxxOrganizationQuery()
    {
        List<Campus> campusList = this.organizationService.ggxxOrganizationQuery();
        if(campusList.size() > 0){
            for (Campus campus : campusList) {
                // 递归部门，查询专业-班级
                if(campus.getChilds().size() > 0){
                    for (Department child : campus.getChilds()) {
                        List<Department> departments = this.departmentService.selectObjByParentDm(child.getDM());
                        if(departments.size() > 0){
                            for (Department campusChild : departments) {
                                this.addSpecialties(campusChild);
                            }
                            List<Department> departments1 = child.getChilds();
                            departments1.addAll(departments);
                        }

                    }
                }
            }
            return ResponseUtil.ok(campusList);
        }
        return ResponseUtil.ok();
    }

    public Department addSpecialties(Department department){
        // 查询专业
        List<Specialty> specialties = this.specialtyService.selectObjByCasecade(department.getDM());
        if(specialties.size() > 0){
            department.getChilds().addAll(specialties);
        }
        return department;
    }

    @ApiOperation("部门信息")
    @GetMapping("/detail")
    public Object detail(@RequestParam String bmlx_dm, @RequestParam String dm){
        Map map = new HashMap();

//        Department bm = this.departmentService.selectObjByDm(dm);
//        map.put("bm", bm);
        Bmlx bmlx = this.bmlxService.selectObjByDM(bmlx_dm);
        map.put("bmlx", bmlx);

        List<BmGlry> bmGlry = this.bmGlryService.selectObjByBM_M(dm);
        map.put("bmGlry", bmGlry);

        return ResponseUtil.ok(map);
    }


//    public List<DepartmentMapper> genericDepartment(DepartmentMapper department){
////        List<DepartmentMapper> departments = new ArrayList<>();
//        List<Specialty> specialties = this.specialtyService.selectObjByCasecade(department.getDM());
//        department.setChilds(specialties);
//    }
//
//    public Set<Long> genericGroupId(Long id){
//        Set<Long> ids = new HashSet();
//        ids.add(id);
//        List<Group> groups = this.groupService.queryChild(id);
//        if(groups.size() > 0){
//            for(Group child : groups){
//                Set<Long> cids = genericGroupId(child.getId());
//                ids.addAll(cids);
//                ids.add(child.getId());
//            }
//        }
//        return ids;
//    }
}
