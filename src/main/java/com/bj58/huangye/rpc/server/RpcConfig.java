package com.bj58.huangye.rpc.server;

import com.bj58.huangye.rpc.registry.ServiceDiscovery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhudongchang on 2019-04-15 14:28
 */
public class RpcConfig {

    private static final Logger logger = LoggerFactory.getLogger(RpcConfig.class);

    private String env;

    private String serviceName;

    private String zkConnectionString;

    private String serverAddress;

    public String getServerAddress() {
        return serverAddress;
    }

    public RpcConfig setServerAddress(String serverAddress) {
        this.serverAddress = serverAddress;
        return this;
    }


    private Map<String, Object> handlerMap = new HashMap<>();

    public RpcConfig addService(String interfaceName, Object serviceBean) {
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

    public RpcConfig setEnv(String env) {
        this.env = env;
        return this;
    }

    public String getServiceName() {
        return serviceName;
    }

    public RpcConfig setServiceName(String serviceName) {
        this.serviceName = serviceName;
        return this;
    }

    public String getZkConnectionString() {
        return zkConnectionString;
    }

    public RpcConfig setZkConnectionString(String zkConnectionString) {
        this.zkConnectionString = zkConnectionString;
        return this;
    }

    public void serverInit(){
        RpcInit.serverInit(this);
    }

    public ServiceDiscovery clientInit(){
        return RpcInit.clientInit(this);
    }
}
