package com.metoo.nspm.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author HKK
 * @version 1.0
 * @date 2023-10-19 14:35
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("学生")
public class XsglXs {

    private String DM;
    @ApiModelProperty("学籍号")
    private String XH;
    private String RXNJ;

    @ApiModelProperty("班级-bj表")
    private String SZBJ_M;
    @ApiModelProperty("班级名称-bj表")
    private String SZBJ_MC;

    private String GKKSH;
    private String GKZKZH;
    @ApiModelProperty("姓名")
    private String XM;

    private String CYM;
    private String XMPY;
    @ApiModelProperty("性别代码-dm_xb表")
    private String XB_M;
    @ApiModelProperty("性别名称-dm_xb表")
    private String XB_MC;
    @ApiModelProperty("身份证号")
    private String SFZH;
    private Date CSRQ;
    private String MZDM_M;
    private String JG_M;
    private String ZZMM_M;
    private Date RDTSJ;
    private String CSD;
    private String HJLB_M;
    private String GATDM_M;
    private String LXR;
    private String LXDZ;
    private String YZBM;
    private String LXRDH;
    @ApiModelProperty("手机号码")
    private String SJHM;
    private String DZYX;
    private String KSLB_M;
    private String KSTZ;
    private String JKZK_M;
    private String HYZK_M;
    private Long XZ;
    private String ZYFX;
    private String SFL_M;
    private String SYSF_M;
    private String DQDM_M;
    private String SYDW;
    private String BYLB_M;
    private BigDecimal RXCJ;
    private String TC;
    private BigDecimal SG;
    private BigDecimal TZ;
    private String LQZY;
    private Date RXRQ;
    private String RXFS_M;
    private String XXXS_M;
    private String PYCC_M;
    private String PYLB_M;
    private String PYDX_M;
    private String ZXWYYZ_M;
    private String ZXWYJB_M;
    private String XJH;
    private String BZ;
    private String DLMM;
    private String ZPLJ;
    private String SFZX;
    private String XJZT_M;
    private String RXJJ_M;
    private String TZSBH;
    private String QQH;
    private String WXH;
    private String XYK;
    private String XXK;
    private String YWM;
    private String JZD_M;
    private String XX_M;
    private String ZJXY_M;
    private Date CREATE_TIME;
    private Date UPDATE_TIME;
    private String XXNX;
    private String XSZH;
    private String WHCD_M;
    private String RDJJFZ;
    private String QTBXXS_M;
    private String JSJDJ_M;
    private String DXHWPDW;
    private String DAYH;
    private String DAHH;
    private String CWH_M;
    private String BYBZ;
    private String BXXS_M;
    private String BXLX_M;
    private String PRINT_TZS_IND;
    private String BANK_ACCOUNT;
    private String JDFS_M;
    private String CZR_M;
    private String KHH;
    private String YHKZP;
    private String ZSFS_M;
    private String LQPC_M;
    private String KL_M;
    private String SFZJLX_M;
    private String XSLB_M;
    private String XSDQZT_M;
    private String JTHK_M;
    private String JTDZ;
    private String JTDZ_YZBH;
    private String JZDZ;
    private String JZDZ_YZBH;
    private String KTZ_IND;
    private String PYFS_M;
    private String SFSLB_M;
    private String ZSPC_M;
    private String SDXH_IND;
    private Date SCXH_TIME;
    private String GSBZRXM;
    private String GSBZRDH;
    private String SYDQ;
    private String BFB_IND;
    private String YHJD;
    private String HKSZD_M;
    private String ZSXZ;
    private String ZY_M;
    private String YXPC_M;
    private String XXFS_M;
    private Date YJBYRQ;
    private String LQZYBZ;
    private String SSMC;
    private String KHHHH;
}
