package com.bj58.huangye.rpc.test.server;

import com.bj58.huangye.rpc.server.RpcServer;
import com.bj58.huangye.rpc.registry.ServiceRegistry;
import com.bj58.huangye.rpc.test.client.HelloService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RpcBootstrapWithoutSpring {
    private static final Logger logger = LoggerFactory.getLogger(RpcBootstrapWithoutSpring.class);

    public static void main(String[] args) {
        String serverAddress = "127.0.0.1:18866";
        ServiceRegistry serviceRegistry = new ServiceRegistry("10.126.102.152:3181,10.126.91.8:3181,10.126.91.84:3181");
        RpcServer rpcServer = new RpcServer(serverAddress, serviceRegistry);
        HelloService helloService = new HelloServiceImpl();
        rpcServer.addService("com.nettyrpc.test.client.HelloService", helloService);
        try {
            rpcServer.start();
        } catch (Exception ex) {
            logger.error("Exception: {}", ex);
        }
    }
}
