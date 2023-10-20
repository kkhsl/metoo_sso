package com.metoo.nspm.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author HKK
 * @version 1.0
 * @date 2023-10-16 15:10
 */
@Data
@Accessors
@AllArgsConstructor
@NoArgsConstructor
public class Campus {

    @ApiModelProperty("代码")
    private String DM;
    @ApiModelProperty("校区编号")
    private String XQBH;
    @ApiModelProperty("名称")
    private String MC;
    @ApiModelProperty("地址")
    private String DZ;
    @ApiModelProperty("邮编")
    private String YZBM;
    @ApiModelProperty("备注")
    private String BZ;
    @ApiModelProperty("校区简称")
    private String JC;
    private Date CREATE_TIME;
    private Date UPDATE_TIME;
    private String CZR_M;
    private String XX_M;

    @ApiModelProperty("部门类型")
    private String MBLX_M;

    private List<Department> childs = new ArrayList();
}
