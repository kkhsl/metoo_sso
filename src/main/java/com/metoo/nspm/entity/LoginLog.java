package com.metoo.nspm.entity;

import com.metoo.nspm.core.domain.IdEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author HKK
 * @version 1.0
 * @date 2023-10-20 9:39
 */
@ApiModel("登录日志")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginLog extends IdEntity {

    @ApiModelProperty("登录名")
    private String loginName;
    @ApiModelProperty("登录时间")
    private Date loginTime;
    @ApiModelProperty("登录Ip")
    private String loginIp;

}
