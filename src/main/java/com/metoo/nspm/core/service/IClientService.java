package com.metoo.nspm.core.service;

import com.github.pagehelper.Page;
import com.metoo.nspm.dto.ClientDTO;
import com.metoo.nspm.entity.Client;

public interface IClientService {

    Page<Client> selectObjByConditionQuery(ClientDTO dto);
}
