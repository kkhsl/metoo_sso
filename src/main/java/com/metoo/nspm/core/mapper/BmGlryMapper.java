package com.metoo.nspm.core.mapper;

import com.metoo.nspm.entity.BmGlry;

import java.util.List;

/**
 * @author HKK
 * @version 1.0
 * @date 2023-10-18 15:41
 */
public interface BmGlryMapper {

    List<BmGlry> selectObjByBM_M(String bm_m);

}
