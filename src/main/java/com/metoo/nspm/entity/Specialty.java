package com.metoo.nspm.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

/**
 * @author HKK
 * @version 1.0
 * @date 2023-10-16 15:23
 */
@Data
@Accessors
@AllArgsConstructor
@NoArgsConstructor
public class Specialty {

    @ApiModelProperty("代码")
    private String DM;
    private String GBZY_M;
    private String ZYBH;
//    private String ZWMC;

    @ApiModelProperty("名称")
    private String MC;

    private String ZWJC;
    private String YWMC;
    private String YWJC;
    private String PYCC_M;
    private String XZ;
    private String SSYX_M;
    private String XWMC;
    private String XK_M;
    private String SYXW_M;
    private String BZ;
    private String ZT;
    private String SF_IND;
    private String YXFX_IND;
    private String GBZY_GZ_M;
    private String GBZY_ZZ_M;
    private String GBZY_TYPE;
    private String CREATE_TIME;
    private String UPDATE_TIME;
    private String CZR_M;
    private String QQQH;
    private String GBZY_YJS_M;


    @ApiModelProperty("部门类型")
    private String BMLX_M;

    private List<Class> childs = new ArrayList<>();
}
