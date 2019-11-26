package common.registry.impl;

import common.loadbalance.LoadBalance;
import common.loadbalance.impl.RandomLoadBalance;
import common.registry.ServiceRegisterCenter;
import common.registry.ZkConfig;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: changjiale
 * @create: 2019/11/25 18:51
 * @description:
 */
public class  ServiceRegisterCenterImpl implements ServiceRegisterCenter {

    List<String> repos = new ArrayList<>();

    private CuratorFramework curatorFramework;

    public ServiceRegisterCenterImpl() {
        curatorFramework = CuratorFrameworkFactory.builder()
                .connectString(ZkConfig.addr).sessionTimeoutMs(4000)
                .retryPolicy(new ExponentialBackoffRetry(1000, 10))
                .build();
        curatorFramework.start();
    }

    @Override
    public void register(String serverName, String serverAddress) {
        //目录节点为持久化节点， ip节点为临时节点
        String serverPath = ZkConfig.ZK_REGISTER_PATH.concat("/").concat(serverName);
        try {
            if (curatorFramework.checkExists().forPath(serverPath) == null ) {
                curatorFramework.create().creatingParentsIfNeeded()
                        .withMode(CreateMode.PERSISTENT).forPath(serverPath, "0".getBytes());
            }
            String addr = serverPath.concat("/").concat(serverAddress);
            String rsNode = curatorFramework.create().withMode(CreateMode.EPHEMERAL)
                    .forPath(addr, "0".getBytes());
            System.out.println("服务注册成功," + rsNode);
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public String discover(String serverName) {
        String path = ZkConfig.ZK_REGISTER_PATH.concat("/").concat(serverName);
        try {
            repos = curatorFramework.getChildren().forPath(path);
        }catch (Exception e) {
            e.printStackTrace();
        }
        registerWatch(path);
        LoadBalance loadBalance = new RandomLoadBalance();
        return loadBalance.select(repos);

    }

    /**
     *监听zk节点变化
     * @param path
     */
    private void registerWatch(final String path) {
        PathChildrenCache childrenCache = new PathChildrenCache(curatorFramework, path, true);
        PathChildrenCacheListener childrenCacheListener = new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework curatorFramework, PathChildrenCacheEvent pathChildrenCacheEvent) throws Exception {
                repos = curatorFramework.getChildren().forPath(path);
            }
        };
        childrenCache.getListenable().addListener(childrenCacheListener);
        try {
            childrenCache.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
