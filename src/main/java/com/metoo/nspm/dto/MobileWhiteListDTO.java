package com.metoo.nspm.dto;

import com.metoo.nspm.dto.page.PageDto;
import com.metoo.nspm.entity.Group;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors
@AllArgsConstructor
@NoArgsConstructor
public class MobileWhiteListDTO extends PageDto<Group> {

    private Long id;

    @ApiModelProperty("手机号")
    private String number;

    @ApiModelProperty("手机号类型 0：移动 1：联动 2：电信")
    private Integer type;

    @ApiModelProperty("手机号来源：0：个人注册 ")
    private Integer from;
}
