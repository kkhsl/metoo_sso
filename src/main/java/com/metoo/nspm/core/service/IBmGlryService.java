package com.metoo.nspm.core.service;

import com.metoo.nspm.entity.BmGlry;

import java.util.List;

/**
 * @author HKK
 * @version 1.0
 * @date 2023-10-18 15:40
 */
public interface IBmGlryService {

    List<BmGlry> selectObjByBM_M(String bm_m);
}
