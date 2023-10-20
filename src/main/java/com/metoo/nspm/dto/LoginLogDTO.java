package com.metoo.nspm.dto;

/**
 * @author HKK
 * @version 1.0
 * @date 2023-10-20 9:49
 */

import com.metoo.nspm.dto.page.PageDto;
import com.metoo.nspm.entity.LoginLog;
import com.metoo.nspm.entity.XsglXs;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class LoginLogDTO extends PageDto<LoginLog> {

    @ApiModelProperty("登录名")
    private String loginName;
    @ApiModelProperty("登录时间")
    private Date loginTime;
    @ApiModelProperty("登录Ip")
    private String loginIp;

    @ApiModelProperty("开始时间")
    private Date startTime;
    @ApiModelProperty("截止时间")
    private Date endTime;

}
