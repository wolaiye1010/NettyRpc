package com.bj58.huangye.rpc.registry;

import com.bj58.huangye.rpc.server.RpcInit;

/**
 * ZooKeeper constant
 *
 * @author huangyong
 */
public class Constant {

    /**
     * 16w
     */
    public static final int maxFrameLength=1024*1024*16;

    public static final int ZK_SESSION_TIMEOUT = 5000;

    private static final String ZK_REGISTRY_PATH = "/registry";

    public static String getZkRegistryPath() {
        return String.format("%s/%s/%s",
                ZK_REGISTRY_PATH
                ,RpcInit.getRpcConfig().getServiceName()
                ,RpcInit.getRpcConfig().getEnv()
        );
    }
}
