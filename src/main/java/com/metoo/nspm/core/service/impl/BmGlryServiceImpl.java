package com.metoo.nspm.core.service.impl;

import com.metoo.nspm.core.mapper.BmGlryMapper;
import com.metoo.nspm.core.service.IBmGlryService;
import com.metoo.nspm.entity.BmGlry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author HKK
 * @version 1.0
 * @date 2023-10-18 15:41
 */
@Service
@Transactional
public class BmGlryServiceImpl implements IBmGlryService {

    @Autowired
    private BmGlryMapper bmGlryMapper;

    @Override
    public List<BmGlry> selectObjByBM_M(String bm_m) {
        return this.bmGlryMapper.selectObjByBM_M(bm_m);
    }
}
