package com.metoo.nspm.entity;

import com.metoo.nspm.core.domain.IdEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author Administrator
 * @version 1.0
 * @date 2023-10-11 9:58
 */
@ApiModel("组织类型")
@Data
@Accessors
@AllArgsConstructor
@NoArgsConstructor
public class GroupType extends IdEntity
{
    private String name;
    private Integer type;
    private Long parentId;
}
