package com.bj58.huangye.rpc.registry;

import org.apache.zookeeper.CreateMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 服务注册
 *
 * @author huangyong
 * @author luxiaoxun
 */
public class ServiceRegistry {

    private static final Logger logger = LoggerFactory.getLogger(ServiceRegistry.class);

    private ZkService zkService=ZkService.getInstance();

    public void register(String data) {
        if (null==data) {
            return;
        }

        zkService.setNode(Constant.getZkRegistryPath()+"/"+getIp(),data, CreateMode.EPHEMERAL);
    }

    private static String  getIp(){
        InetAddress inetAddress = null;
        try {
            inetAddress = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }

        return inetAddress.getHostAddress();
    }
}