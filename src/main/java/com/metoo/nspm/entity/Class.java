package com.metoo.nspm.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author HKK
 * @version 1.0
 * @date 2023-10-16 15:26
 */
@Data
@Accessors
@AllArgsConstructor
@NoArgsConstructor
public class Class {

    @ApiModelProperty("代码")
//    private String BJDM;
    private String DM;
    private String NJ;
    private String XQ_M;
    private String BJBH;
//    private String BJMC;

    @ApiModelProperty("名称")
    private String MC;

    private String BJJC;
    private String YX_M;
    private String ZY_M;
    private String XZ;
    private String FDY_M;
    private String BZR_M;
    private String QQQH;
    private String CREATE_TIME;
    private String UPDATE_TIME;
    private String CZR_M;
    private String BZ;
    private String YSRS;
    private String ZT;
    private String SXBJ_IND;

    @ApiModelProperty("部门类型")
    private String BMLX_M;

}
