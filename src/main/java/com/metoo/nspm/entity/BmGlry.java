package com.metoo.nspm.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author HKK
 * @version 1.0
 * @date 2023-10-18 15:38
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("部门管理人员")
public class BmGlry {

    private String DM;
    private String BM_M;
    private String ZW_M;
    private String GWBZ;

    private String GLRY_M;
    @ApiModelProperty("管理人员名称")
    private String GLRY_MC;

    private String BGDD;
    private String CREATE_TIME;
    private String UPDATE_TIME;
    private String CZR_M;

}
