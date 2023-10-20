package com.metoo.nspm.core.service.impl;

import com.metoo.nspm.core.mapper.OrganizationMapper;
import com.metoo.nspm.core.service.IOrganizationService;
import com.metoo.nspm.entity.Campus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author HKK
 * @version 1.0
 * @date 2023-10-16 15:35
 */
@Service
@Transactional
public class OrganizationServiceImpl implements IOrganizationService {

    @Resource
    private OrganizationMapper organizationMapper;

    @Override
    public List<Campus> ggxxOrganizationQuery() {
        return this.organizationMapper.ggxxOrganizationQuery();
    }
}
