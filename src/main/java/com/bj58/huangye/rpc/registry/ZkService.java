package com.bj58.huangye.rpc.registry;

import com.bj58.huangye.rpc.server.RpcInit;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;

/**
 * Created by zhudongchang on 2019-04-15 15:26
 */
public class ZkService {

    private static final Logger logger = LoggerFactory.getLogger(ZkService.class);

    private CountDownLatch latch = new CountDownLatch(1);

    private static ZooKeeper zk;
    private static ZkService zkService;

    public static ZkService getInstance(){
        if(null==zkService){
            zkService=new ZkService();
            zk=zkService.connectServer();
        }
        return zkService;
    }

    private ZooKeeper connectServer() {
        ZooKeeper zk = null;
        try {
            zk = new ZooKeeper(RpcInit.getServerConfig().getZkConnectionString(), Constant.ZK_SESSION_TIMEOUT, new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                    if (event.getState() == Event.KeeperState.SyncConnected) {
                        latch.countDown();
                    }
                }
            });
            latch.await();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return zk;
    }


    public void setNode(String path,String data){
        Stat stat = null;
        try {
            stat = zk.exists(Constant.getZkRegistryPath(), false);
            if(null==stat){
                createNode(path,data);
            }else{
                zk.setData(path,data.getBytes(),stat.getVersion());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    private void createNode(String path, String data) {
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
