package com.bj58.huangye.rpc.server;

/**
 * Created by zhudongchang on 2019-04-15 14:32
 */
public class RpcInit {

    public ServerConfig builder(){
        return new ServerConfig();
    }


    private static ServerConfig serverConfig;

    public static ServerConfig getServerConfig() {
        return serverConfig;
    }

    public static void init(ServerConfig serverConfig){
        RpcInit.serverConfig=serverConfig;
    }

}
