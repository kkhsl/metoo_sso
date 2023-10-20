package com.metoo.nspm.entity;

import com.metoo.nspm.core.config.annotation.excel.ExcelExport;
import com.metoo.nspm.core.config.annotation.excel.ExcelImport;
import com.metoo.nspm.core.domain.IdEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel("手机号白名单：")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MobileWhiteList extends IdEntity {

    @ExcelImport(value = "手机号", unique = true, required = true)
    @ApiModelProperty("手机号")
    private String number;

    @ApiModelProperty("手机号类型 0：移动 1：联动 2：电信")
    private Integer type;

    @ApiModelProperty("手机号来源：0：个人注册 ")
    private Integer from;

}
