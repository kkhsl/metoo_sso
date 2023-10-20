package com.metoo.nspm.core.service;

import com.github.pagehelper.Page;
import com.metoo.nspm.dto.MobileWhiteListDTO;
import com.metoo.nspm.entity.MobileWhiteList;

import java.util.List;

public interface IMobileWhiteListService {

    MobileWhiteList selectObjByMobile(String mobile);

    Page<MobileWhiteList> selectObjConditionQuery(MobileWhiteListDTO instance);

    boolean save(MobileWhiteList instance);

    boolean update(MobileWhiteList install);

    boolean delete(Long id);

    int batchInsert(List<MobileWhiteList> instance);

    void truncateTable();
}
