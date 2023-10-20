package com.metoo.nspm.core.mapper;

import com.metoo.nspm.dto.MobileWhiteListDTO;
import com.metoo.nspm.entity.MobileWhiteList;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MobileWhiteListMapper {

    MobileWhiteList selectObjByMobile(String mobile);

    List<MobileWhiteList> selectObjConditionQuery(MobileWhiteListDTO instance);

    int save(MobileWhiteList instance);

    int update(MobileWhiteList install);

    int delete(Long id);

    int batchInsert(List<MobileWhiteList> instance);

    void truncateTable();
}
