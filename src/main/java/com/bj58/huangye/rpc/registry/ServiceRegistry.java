package com.bj58.huangye.rpc.registry;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
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

    private CountDownLatch latch = new CountDownLatch(1);

    private String registryAddress;

    public ServiceRegistry(String registryAddress) {
        this.registryAddress = registryAddress;
    }

    public void register(String data) {
        if (data != null) {
            ZooKeeper zk = connectServer();
            if (zk != null) {
                setNode(zk,Constant.ZK_DATA_PATH,data);
            }
        }
    }

    private ZooKeeper connectServer() {
        ZooKeeper zk = null;
        try {
            zk = new ZooKeeper(registryAddress, Constant.ZK_SESSION_TIMEOUT, new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                    if (event.getState() == Event.KeeperState.SyncConnected) {
                        latch.countDown();
                    }
                }
            });
            latch.await();
        } catch (IOException e) {
            logger.error("", e);
        }
        catch (InterruptedException ex){
            logger.error("", ex);
        }
        return zk;
    }

    private void AddRootNode(ZooKeeper zk){
        try {
            Stat s = zk.exists(Constant.getZkRegistryPath(), false);
            if (s == null) {
                zk.create(Constant.getZkRegistryPath(), new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
        } catch (KeeperException e) {
            logger.error(e.toString());
        } catch (InterruptedException e) {
            logger.error(e.toString());
        }
    }

    private void setNode(ZooKeeper zk,String path,String data){
        Stat stat = null;
        try {
            stat = zk.exists(Constant.getZkRegistryPath(), false);
            if(null==stat){
                createNode(zk,path,data);
            }else{
                zk.setData(path,data.getBytes(),stat.getVersion()+1);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    private void createNode(ZooKeeper zk,String path, String data) {
        String[] split = path.split("/");
        String zkPath="";
        for (int i = 0; i < split.length; i++) {
            String item = split[i];
            if("".equals(item)){
                continue;
            }
            zkPath+="/"+item;
            try {
                Stat stat = zk.exists(zkPath, false);
                if(null==stat){
                    boolean isLast=i==(split.length-1);
                    if(isLast){
                        zk.create(zkPath, data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
                    }else{
                        zk.create(zkPath, new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}