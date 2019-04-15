package com.bj58.huangye.rpc.registry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

        zkService.setNode(Constant.ZK_DATA_PATH,data);
    }
}