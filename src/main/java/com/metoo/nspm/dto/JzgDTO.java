package com.metoo.nspm.dto;

import com.metoo.nspm.dto.page.PageDto;
import com.metoo.nspm.entity.Jzg;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author HKK
 * @version 1.0
 * @date 2023-10-19 16:11
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class JzgDTO extends PageDto<Jzg> {

    @ApiModelProperty("姓名")
    private String XM;
}
