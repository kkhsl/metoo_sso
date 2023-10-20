package com.metoo.nspm.core.service.impl;

import com.metoo.nspm.core.mapper.SpecialtyMapper;
import com.metoo.nspm.core.service.ISpecialtyService;
import com.metoo.nspm.entity.Specialty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author HKK
 * @version 1.0
 * @date 2023-10-17 11:54
 */

@Service
@Transactional
public class SpecialtyServiceImpl implements ISpecialtyService {

    @Autowired
    private SpecialtyMapper specialtyMapper;

    @Override
    public List<Specialty> selectObjByCasecade(String dm) {
        return this.specialtyMapper.selectObjByCasecade(dm);
    }

    @Override
    public Specialty selectObjByConcat(String dm) {
        return this.specialtyMapper.selectObjByConcat(dm);
    }

}
