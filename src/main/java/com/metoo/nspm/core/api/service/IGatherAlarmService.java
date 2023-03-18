package com.metoo.nspm.core.api.service;

import com.metoo.nspm.core.config.socket.NoticeWebsocketResp;

public interface IGatherAlarmService {

    NoticeWebsocketResp getAlarms(String params);
}
