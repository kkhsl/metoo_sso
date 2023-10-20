package com.metoo.nspm.core.service;

import com.metoo.nspm.entity.Campus;

import java.util.List;

/**
 * @author HKK
 * @version 1.0
 * @date 2023-10-16 15:35
 */
public interface IOrganizationService {

    /**
     * fetch data by rule id
     *
     * @return Result<List<Campus>>
     */
    List<Campus> ggxxOrganizationQuery();
}
