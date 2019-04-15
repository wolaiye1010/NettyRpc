package com.bj58.huangye.rpc.server;

import com.bj58.huangye.rpc.registry.ServiceDiscovery;
import com.bj58.huangye.rpc.registry.ServiceRegistry;

import java.util.Map;

/**
 * Created by zhudongchang on 2019-04-15 14:32
 */
public class RpcInit {

    public static RpcConfig builder(){
        return new RpcConfig();
    }

    private static RpcConfig rpcConfig;

    public static RpcConfig getRpcConfig() {
        return rpcConfig;
    }

    public static void serverInit(RpcConfig rpcConfig){
        RpcInit.rpcConfig = rpcConfig;
        ServiceRegistry serviceRegistry = new ServiceRegistry();
        RpcServer rpcServer = new RpcServer(rpcConfig.getServerAddress(), serviceRegistry);

        Map<String, Object> handlerMap = rpcConfig.getHandlerMap();
        for (Map.Entry<String, Object> stringObjectEntry : handlerMap.entrySet()) {
            rpcServer.addService(stringObjectEntry.getKey(),stringObjectEntry.getValue());
        }

        try {
            rpcServer.start();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }


    private static ServiceDiscovery serviceDiscovery;

    public static ServiceDiscovery clientInit(RpcConfig rpcConfig){
        if(null!=serviceDiscovery){
            return serviceDiscovery;
        }

        RpcInit.rpcConfig = rpcConfig;
        RpcInit.serviceDiscovery = new ServiceDiscovery();
        return serviceDiscovery;
    }

}
