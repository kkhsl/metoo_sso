package com.metoo.nspm.entity;

import com.metoo.nspm.core.domain.IdEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author HKK
 * @version 1.0
 * @date 2023-10-12 10:34
 */
@Data
@Accessors
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("组织架构节点信息")
public class GroupNode extends IdEntity {

    // 有用户删除时，这里动态删除；暂时不做
    @ApiModelProperty("部门主管")
    private String departmentManager;
    @ApiModelProperty("班主任")
    private String classTearch;
    @ApiModelProperty("任课老师")
    private String teacher;
    @ApiModelProperty("班长")
    private String monitor;
    @ApiModelProperty("家委会会长")
    private String president;
    @ApiModelProperty("年级组长")
    private String gradGroupLeader;
    @ApiModelProperty("组长")
    private String groupLeader;

    @ApiModelProperty("组织架构主键")
    private Long groupId;
}
