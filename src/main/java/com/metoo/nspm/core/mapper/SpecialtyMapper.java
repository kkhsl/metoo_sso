package com.metoo.nspm.core.mapper;

import com.metoo.nspm.entity.Specialty;

import java.util.List;

/**
 * @author HKK
 * @version 1.0
 * @date 2023-10-17 11:55
 */
public interface SpecialtyMapper {

    List<Specialty> selectObjByCasecade(String bm_dm);

    Specialty selectObjByConcat(String dm);
}
