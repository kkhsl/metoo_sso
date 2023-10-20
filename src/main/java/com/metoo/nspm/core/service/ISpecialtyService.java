package com.metoo.nspm.core.service;

import com.metoo.nspm.entity.Specialty;

import java.util.List;

/**
 * @author HKK
 * @version 1.0
 * @date 2023-10-17 11:53
 */
public interface ISpecialtyService {

    List<Specialty> selectObjByCasecade(String dm);

    Specialty selectObjByConcat(String dm);
}
