package com.bj58.huangye.rpc.server;

/**
 * Created by zhudongchang on 2019-04-15 14:28
 */
public class ServerConfig {

    private String env;

    private String serviceName;

    private String zkConnectionString;

    public String getEnv() {
        return env;
    }

    public ServerConfig setEnv(String env) {
        this.env = env;
        return this;
    }

    public String getServiceName() {
        return serviceName;
    }

    public ServerConfig setServiceName(String serviceName) {
        this.serviceName = serviceName;
        return this;
    }

    public String getZkConnectionString() {
        return zkConnectionString;
    }

    public ServerConfig setZkConnectionString(String zkConnectionString) {
        this.zkConnectionString = zkConnectionString;
        return this;
    }

    public void init(){
        RpcInit.init(this);
    }
}
