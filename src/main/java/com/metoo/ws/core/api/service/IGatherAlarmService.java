package com.metoo.ws.core.api.service;

import com.metoo.ws.core.config.socket.NoticeWebsocketResp;

public interface IGatherAlarmService {

    NoticeWebsocketResp getAlarms(String params);
}
