package com.bj58.huangye.rpc.server;

import com.bj58.huangye.rpc.registry.ServiceRegistry;

import java.util.Map;

/**
 * Created by zhudongchang on 2019-04-15 14:32
 */
public class RpcInit {

    public static ServerConfig builder(){
        return new ServerConfig();
    }

    private static ServerConfig serverConfig;

    public static ServerConfig getServerConfig() {
        return serverConfig;
    }

    public static void init(ServerConfig serverConfig){
        RpcInit.serverConfig=serverConfig;
        ServiceRegistry serviceRegistry = new ServiceRegistry(serverConfig.getZkConnectionString());
        RpcServer rpcServer = new RpcServer(serverConfig.getServerAddress(), serviceRegistry);

        Map<String, Object> handlerMap = serverConfig.getHandlerMap();
        for (Map.Entry<String, Object> stringObjectEntry : handlerMap.entrySet()) {
            rpcServer.addService(stringObjectEntry.getKey(),stringObjectEntry.getValue());
        }

        try {
            rpcServer.start();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

}
