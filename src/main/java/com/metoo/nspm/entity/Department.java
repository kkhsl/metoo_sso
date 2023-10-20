package com.metoo.nspm.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author HKK
 * @version 1.0
 * @date 2023-10-16 15:15
 */
@Data
@Accessors
@AllArgsConstructor
@NoArgsConstructor
public class Department<T> {


    @ApiModelProperty("代码")
    private String DM;
    private String FJGDM;
    @ApiModelProperty("部门级别")
    private Integer BMJB;
    @ApiModelProperty("部门类型")
    private String BMLX_M;
    @ApiModelProperty("部门编号")
    private String BMBH;
//    @ApiModelProperty("名称")
//    private String ZWMC;

    @ApiModelProperty("名称")
    private String MC;

    private String BC;
    @ApiModelProperty("英文名称")
    private String YWMC;
    @ApiModelProperty("联系人")
    private String LXR;
    @ApiModelProperty("联系电话")
    private String DH;
    @ApiModelProperty("传真")
    private String CZ;
    @ApiModelProperty("电子邮箱")
    private String DZYJ;
    @ApiModelProperty("地址")
    private String DZ;
    @ApiModelProperty("邮编")
    private String YB;
    @ApiModelProperty("备注")
    private String BZ;
    private String SSXQ_M;
    private String SJ_M;
    private String YZ_M;
    private String STAMP_PATH;
    @ApiModelProperty("QQ群号")
    private String QQQH;
    private Double PX;
    private Date CREATE_TIME;
    private Date UPDATE_TIME;
    private String CZR_M;
    private String ZT;
    @ApiModelProperty("部门代号")
    private String BM_CODE;
    private String XQ_M;
    private String SJ_DM;


    private List<Department> childDepartments = new ArrayList<>();

    private Department child;


    private List<T> childs = new ArrayList<>();

}
