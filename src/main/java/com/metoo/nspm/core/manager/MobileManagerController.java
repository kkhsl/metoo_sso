package com.metoo.nspm.core.manager;

import com.github.pagehelper.Page;
import com.metoo.nspm.core.config.utils.ResponseUtil;
import com.metoo.nspm.core.config.utils.file.DownLoadFileUtil;
import com.metoo.nspm.core.config.utils.poi.ExcelUtils;
import com.metoo.nspm.core.service.IMobileWhiteListService;
import com.metoo.nspm.core.service.ISysConfigService;
import com.metoo.nspm.core.utils.query.PageInfo;
import com.metoo.nspm.dto.MobileWhiteListDTO;
import com.metoo.nspm.entity.MobileWhiteList;
import com.metoo.nspm.entity.NetworkElement;
import com.metoo.nspm.entity.SysConfig;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@RequestMapping("/admin/mobile")
@RestController
public class MobileManagerController {

    @Autowired
    private IMobileWhiteListService mobileWhiteListService;
    @Autowired
    private ISysConfigService configService;


//    @ApiOperation("手机号列表")
//    @GetMapping("/list")
//    public MobileWhiteList list(String mobile){
//        MobileWhiteList mobileWhiteList = mobileWhiteListService.selectObjByMobile(mobile);
//        return mobileWhiteList;
//    }

    @ApiOperation("开启/禁用")
    @GetMapping("/detail")
    public Object detail(){
        Map map = new HashMap();
        map.put("disable", this.configService.select().isPhoneDisable());
        return ResponseUtil.ok(map);
    }

    @ApiOperation("手机号列表")
    @PostMapping("/list")
    public Object list(@RequestBody(required=false) MobileWhiteListDTO dto){
        if(dto == null){
            dto = new MobileWhiteListDTO();
        }
        Page<MobileWhiteList> page = this.mobileWhiteListService.selectObjConditionQuery(dto);
        if(page.getResult().size() > 0) {
            return ResponseUtil.ok(new PageInfo<NetworkElement>(page));
        }
        return  ResponseUtil.ok();
    }

    @ApiOperation("手机号列表")
    @PostMapping("/save")
    public Object save(@RequestBody MobileWhiteList mobileWhiteList){
        if(mobileWhiteList.getNumber() == null || mobileWhiteList.getNumber().equals("")){
            return ResponseUtil.badArgument("手机号不允许为空");
        }else{
            boolean isValid = validate(mobileWhiteList.getNumber());
            if(!isValid){
                return ResponseUtil.badArgument("手机号格式错误");
            }
        }
        MobileWhiteList obj = mobileWhiteListService.selectObjByMobile(mobileWhiteList.getNumber());
        if(obj != null){
            return ResponseUtil.badArgument("手机号格已存在");
        }
        boolean flag = this.mobileWhiteListService.save(mobileWhiteList);
        if(flag){
            return ResponseUtil.ok();
        }
        return ResponseUtil.error();
    }

    @ApiOperation("手机号列表")
    @DeleteMapping("/delete")
    public Object delete(Long id){
        boolean flag = this.mobileWhiteListService.delete(id);
        if(flag){
            return ResponseUtil.ok();
        }
        return ResponseUtil.error();
    }

    // 批量添加
    @ApiOperation("手机号列表")
    @PostMapping("/batchInsert")
    public int batchInsert(@RequestParam List<MobileWhiteList> t){
        return mobileWhiteListService.batchInsert(t);
    }

    public static boolean validate(String phoneNumber) {
//        String pattern = "^[1]\\d{10}$";
        String pattern = "^1[3-9]\\d{9}$";

        return Pattern.matches(pattern, phoneNumber);
    }
    // excel导入
    @ApiOperation("Excel导入")
    @PostMapping("/import")
    public Object importExcel(@RequestPart("file") MultipartFile file) throws Exception {
        if(!file.isEmpty()){
            String fileName = file.getOriginalFilename().toLowerCase();
            String suffix = fileName.substring(fileName.lastIndexOf(".")+1).toLowerCase();
            if (suffix.equals("xlsx") || suffix.equals("xls")) {
                List<MobileWhiteList> MobileWhiteLists = ExcelUtils.readMultipartFile(file, MobileWhiteList.class);
                if(MobileWhiteLists.size() > 0){
                    // 清空数据
                    try {
                        this.mobileWhiteListService.truncateTable();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    String msg = "";
                    List<MobileWhiteList> mobiles = new ArrayList<>();
                    for (int i = 0; i < MobileWhiteLists.size(); i++) {
                        MobileWhiteList mobileWhite = MobileWhiteLists.get(i);
                        if (mobileWhite.getNumber() == null || mobileWhite.getNumber().equals("")) {
                            msg = "第" + (i + 2) + "行, 手机号码不能为空";
                            break;
                        } else {
                            boolean isValid = validate(mobileWhite.getNumber());
                            if(!isValid){
                                msg = "第" + (i + 2) + "行, 手机号码格式错误";
                                break;
                            }
                            MobileWhiteList obj = this.mobileWhiteListService.selectObjByMobile(mobileWhite.getNumber());
                            if (obj != null) {
//                                msg = "第" + (i + 2) + "行, 手机号码已存在";
//                                break;
                                continue;
                            }
                        }
                        mobiles.add(mobileWhite);
                    }
                    if(msg.isEmpty()){
                        // 批量插入NE
                        if(mobiles.size() > 0){
                            int i = this.mobileWhiteListService.batchInsert(mobiles);
                            if(i > 0){
                                return ResponseUtil.ok();
                            }else{
                                return ResponseUtil.error();
                            }
                        }
                        return ResponseUtil.ok();
                    }else{
                        return ResponseUtil.badArgument(msg);
                    }
                }else{
                    return ResponseUtil.badArgument("文件数据为空");
                }
            }else{
//                return ResponseUtil.badArgument("文件格式错误，请使用标准模板上传");
                return ResponseUtil.badArgument("文件格式错误");
            }
        }
        return ResponseUtil.badArgument("文件不存在");
    }

    @PutMapping("/disable")
    public Object disable(){
        SysConfig sysConfig = configService.select();
        if(sysConfig.isPhoneDisable()){
            sysConfig.setPhoneDisable(false);
        }else{
            sysConfig.setPhoneDisable(true);
        }
        configService.update(sysConfig);
        return ResponseUtil.ok();
    }

    @Value("${batchImportMobileFileName}")
    private String batchImportMobileFileName;
    @Value("${batchImportFilePath}")
    private String batchImportFilePath;


    @ApiOperation("模板下载")
    @GetMapping("/downTemp")
    public Object downTemplate( HttpServletResponse response) {
        boolean flag = DownLoadFileUtil.downloadTemplate(this.batchImportFilePath, this.batchImportMobileFileName, response);
        if(flag){
            return ResponseUtil.ok();
        }else{
            return ResponseUtil.error();
        }
    }
}
