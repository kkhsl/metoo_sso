package com.metoo.nspm.core.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.metoo.nspm.core.mapper.MobileWhiteListMapper;
import com.metoo.nspm.core.service.IMobileWhiteListService;
import com.metoo.nspm.dto.MobileWhiteListDTO;
import com.metoo.nspm.entity.MobileWhiteList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class MobileWhiteListServiceImpl implements IMobileWhiteListService {

    @Autowired
    private MobileWhiteListMapper mobileWhiteListMapper;


    @Override
    public MobileWhiteList selectObjByMobile(String mobile) {
        return this.mobileWhiteListMapper.selectObjByMobile(mobile);
    }

    @Override
    public Page<MobileWhiteList> selectObjConditionQuery(MobileWhiteListDTO instance) {
        if(instance == null){
            instance = new MobileWhiteListDTO();
        }
        Page<MobileWhiteList> page = PageHelper.startPage(instance.getCurrentPage(), instance.getPageSize());
        this.mobileWhiteListMapper.selectObjConditionQuery(instance);
        return page;
    }

    @Override
    public boolean save(MobileWhiteList instance) {
        if(instance.getId() != null && !instance.getId().equals("")){
            instance.setAddTime(new Date());
        }

        if(instance.getId() == null){
            try {
                this.mobileWhiteListMapper.save(instance);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }else{
            try {
                this.mobileWhiteListMapper.update(instance);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
    }

    @Override
    public boolean update(MobileWhiteList instance) {
        try {
            this.mobileWhiteListMapper.update(instance);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Long id) {
        try {
            this.mobileWhiteListMapper.delete(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public int batchInsert(List<MobileWhiteList> instances) {
        for (MobileWhiteList instance : instances) {
            instance.setAddTime(new Date());
        }
        try {
            int i = this.mobileWhiteListMapper.batchInsert(instances);
            return i;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public void truncateTable() {
        this.mobileWhiteListMapper.truncateTable();
    }
}
