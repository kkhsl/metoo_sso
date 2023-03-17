package com.metoo.ws.core.api.service.impl;

import com.metoo.ws.core.api.service.IGatherAlarmService;
import com.metoo.ws.core.config.http.RestTemplateUtil;
import com.metoo.ws.core.config.socket.NoticeWebsocketResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GatherAlarmServiceImpl implements IGatherAlarmService {

    @Autowired
    private RestTemplateUtil restTemplateUtil;

    @Override
    public NoticeWebsocketResp getAlarms(String params) {
        String url = "/websocket/api/gather/alarm/list";
        NoticeWebsocketResp result = restTemplateUtil.getObjByStr(url, params);
        return result;
    }
}
