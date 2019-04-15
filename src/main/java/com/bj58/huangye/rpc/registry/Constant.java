package com.bj58.huangye.rpc.registry;

/**
 * ZooKeeper constant
 *
 * @author huangyong
 */
public class Constant {

    public static final int ZK_SESSION_TIMEOUT = 5000;

    private static final String ZK_REGISTRY_PATH = "/registry";

    public static final String ZK_DATA_PATH = getZkRegistryPath() + "/data";

    private static String env;

    public static String getEnv() {
        return env;
    }

    public static void setEnv(String env) {
        Constant.env = env;
    }

    public static String getZkRegistryPath() {
        return ZK_REGISTRY_PATH+"/"+env;
    }
}
