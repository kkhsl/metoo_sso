package com.metoo.nspm.core.api.service;

import com.metoo.nspm.core.config.socket.NoticeWebsocketResp;

public interface INetworkElementService {

    NoticeWebsocketResp getNeAvailable(String params);

    NoticeWebsocketResp getSnmpSatus(String params);

    NoticeWebsocketResp interfaceEvent(String params);

    NoticeWebsocketResp getNeInterfaceDT(String params);

    NoticeWebsocketResp getTerminalOnline(String params);


}
