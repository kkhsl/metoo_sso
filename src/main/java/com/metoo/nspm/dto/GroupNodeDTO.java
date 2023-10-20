package com.metoo.nspm.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author HKK
 * @version 1.0
 * @date 2023-10-12 16:53
 */
@Data
@Accessors
@AllArgsConstructor
@NoArgsConstructor
public class GroupNodeDTO {

    private Long id;

    // 有用户删除时，这里动态删除；暂时不做
    @ApiModelProperty("部门主管")
    private List<Long> departmentManager;
    @ApiModelProperty("班主任")
    private List<Long> classTearch;
    @ApiModelProperty("任课老师")
    private List<Long> teacher;
    @ApiModelProperty("班长")
    private List<Long> monitor;
    @ApiModelProperty("家委会会长")
    private List<Long> president;
    @ApiModelProperty("年级组长")
    private List<Long> gradGroupLeader;
    @ApiModelProperty("组长")
    private List<Long> groupLeader;

    @ApiModelProperty("组织架构主键")
    private Long groupId;
}
