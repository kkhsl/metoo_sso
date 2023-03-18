package com.metoo.nspm.core.api.service;

import com.metoo.nspm.core.config.socket.NoticeWebsocketResp;

public interface IProblemService {

    NoticeWebsocketResp getProblem(String params);
    NoticeWebsocketResp getProblemCpu(String params);
    NoticeWebsocketResp getProblemLimit(String params);

}
