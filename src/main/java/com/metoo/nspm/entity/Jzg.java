package com.metoo.nspm.entity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author HKK
 * @version 1.0
 * @date 2023-10-19 14:17
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("教职工")
public class Jzg {

    private String DM;
    @ApiModelProperty("工号")
    private String GH;
    @ApiModelProperty("姓名")
    private String XM;
    private String XMQP;
    private String XMJP;
    private String CYM;
    private String YWM;
    @ApiModelProperty("性别代码-dm_xb")
    private String XB_M;
    @ApiModelProperty("性别名称")
    private String XB_MC;
    @ApiModelProperty("部门代码")
    private String SSDW_M;
    @ApiModelProperty("部门名称")
    private String SSDW_MC;
    private Date CSRQ;
    @ApiModelProperty("身份证号")
    private String SFZH;
    private String DZ;
    private String DZYX;
    private String BGDH;
    private String BGDD;
    @ApiModelProperty("电话号码")
    private String YDDH;
    private String JTDH;
    private String SFZG;
    private String CSD;
    private String QQH;
    private String WXH;
    private String XL_M;
    private String XW_M;
    private String ZC_M;
    private String ZZMM_M;
    private String MZ_M;
    private String JG_M;
    private String SZGW_M;
    private String HYZK_M;
    private String BYXX_M;
    private String XX_M;
    private String ZJXY_M;
    private String SFZJLX_M;
    private String JKZK_M;
    private String ZWJB_M;
    private String DQZT_M;
    private String JZGLB_M;
    private String JSLY_M;
    private String GATQW_M;
    private String SFWP;
    private String SFSS;
    private Date CJGZRQ;
    private Date RXRQ;
    private Date CJNY;
    private String GXJSZG;
    private String BZLB_M;
    private String YJFX;
    private String GRJL;
    private String ZPLJ;
    private String BZ;
    private String ZT;
    private String RS_ZT;
    private Date CREATE_TIME;
    private Date UPDATE_TIME;
    private String CZR_M;
    private String RZ_ZT;
    private String ZW_M;
    private String BYXXMC;
    private String ZGXLZYLB_M;
    private String FDYLX_M;
}
