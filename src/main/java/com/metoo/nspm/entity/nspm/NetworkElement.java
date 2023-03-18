package com.metoo.nspm.entity.nspm;

import com.metoo.nspm.core.domain.IdEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
@Accessors
@AllArgsConstructor
@NoArgsConstructor
public class NetworkElement extends IdEntity {

    private String deviceName;
    private String ip;
    private String vendorName;
    private String deviceTypeName;
    private String description;
    @ApiModelProperty("连接类型 默认0：ssh 1：telnet")
    private Integer connectType;
    @ApiModelProperty("端口 22")
    private Integer port;
    private String filter;
    private String interfaceName;
    private Long groupId;
    private String groupName;
    private Long deviceTypeId;
    private DeviceType deviceType;
    private Long vendorId;
    private Long userId;
    private String userName;
    private boolean sync_device;
    private String available; // -1：为获取到状态 0 - (默认) 未知; 1 - 可用; 2 - 不可用。 3:未配置
    private String error;
    private String uuid;
    private String interfaceNames;
    private String flux;
    @ApiModelProperty("凭据Id")
    private Long credentialId;
    @ApiModelProperty("凭据名称")
    private String credentialName;
    @ApiModelProperty("Web登录链接")
    private String webUrl;
    @ApiModelProperty("是否允许连接 默认 false：不允许 true：允许")
    private boolean permitConnect;

    // Excel 导入导出
    private Integer rowNum;
    private String rowData;
    private String rowTips;

    // 端口列表
    private List<Map> interfaces;

    // 分组列表
    private List<Group> groupList = new ArrayList<>();
    // 终端列表
    private List<Terminal> terminalList = new ArrayList<>();


}
