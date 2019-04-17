package com.bj58.huangye.rpc.test.app;

import com.bj58.huangye.rpc.client.RpcClient;
import com.bj58.huangye.rpc.registry.ServiceDiscovery;
import com.bj58.huangye.rpc.server.RpcInit;
import com.bj58.huangye.rpc.test.client.HelloService;
import org.junit.Assert;

/**
 * Created by luxiaoxun on 2016-03-11.
 */
public class Benchmark {

    public static void main(String[] args) throws InterruptedException {
        RpcInit.builder()
                .setEnv("offline")
                .setServiceName("confcenter")
                .setZkConnectionString("10.126.102.152:3181,10.126.91.8:3181,10.126.91.84:3181")
                .clientInit();
        HelloService helloService = RpcClient.create(HelloService.class);
        String result = helloService.hello("World");
        Assert.assertEquals("Hello! World", result);

        System.out.println(result);
        System.exit(0);
    }
}
