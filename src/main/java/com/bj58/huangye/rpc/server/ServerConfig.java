package com.bj58.huangye.rpc.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhudongchang on 2019-04-15 14:28
 */
public class ServerConfig {

    private static final Logger logger = LoggerFactory.getLogger(ServerConfig.class);


    private String env;

    private String serviceName;

    private String zkConnectionString;

    private String serverAddress;

    public String getServerAddress() {
        return serverAddress;
    }

    public ServerConfig setServerAddress(String serverAddress) {
        this.serverAddress = serverAddress;
        return this;
    }


    private Map<String, Object> handlerMap = new HashMap<>();

    public ServerConfig addService(String interfaceName, Object serviceBean) {
        if (!handlerMap.containsKey(interfaceName)) {
            logger.info("Loading service: {}", interfaceName);
            handlerMap.put(interfaceName, serviceBean);
        }

        return this;
    }

    public Map<String, Object> getHandlerMap() {
        return handlerMap;
    }

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
