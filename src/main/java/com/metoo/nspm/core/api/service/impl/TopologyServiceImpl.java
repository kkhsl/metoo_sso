package com.metoo.nspm.core.api.service.impl;

import com.metoo.nspm.core.api.service.ITopologyService;
import com.metoo.nspm.core.config.http.RestTemplateUtil;
import com.metoo.nspm.core.config.socket.NoticeWebsocketResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.lang.Thread.sleep;

@Service
public class TopologyServiceImpl implements ITopologyService {


    @Autowired
    private RestTemplateUtil restTemplateUtil;

    @Override
    public NoticeWebsocketResp getMacDT(String params) {
        String url = "/websocket/api/zabbix/mac/dt";
        NoticeWebsocketResp result = restTemplateUtil.getObjByStr(url, params);
        return result;
    }
}
