package com.bj58.huangye.rpc.test.server;

import com.bj58.huangye.rpc.server.RpcInit;
import com.bj58.huangye.rpc.test.client.HelloService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RpcBootstrapWithoutSpring1 {
    private static final Logger logger = LoggerFactory.getLogger(RpcBootstrapWithoutSpring1.class);

    public static void main(String[] args) {
        HelloService helloService = new HelloServiceImpl();

        RpcInit.builder()
                .setEnv("offline")
                .setServiceName("confcenter")
                .setServerAddress("127.0.0.1:18866")
                .setZkConnectionString("10.126.102.152:3181,10.126.91.8:3181,10.126.91.84:3181")
                .addService(HelloService.class.getName(),helloService)
                .init();
    }
}
