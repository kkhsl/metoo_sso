package com.metoo.nspm.core.service.impl;

import com.metoo.nspm.core.mapper.BmlxMapper;
import com.metoo.nspm.core.service.IBmlxService;
import com.metoo.nspm.entity.Bmlx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author HKK
 * @version 1.0
 * @date 2023-10-18 14:59
 */
@Service
@Transactional
public class BmlxServiceImpl implements IBmlxService {

    @Autowired
    private BmlxMapper bmlxMapper;

    @Override
    public Bmlx selectObjByDM(String dm) {
        return this.bmlxMapper.selectObjByDM(dm);
    }

}
