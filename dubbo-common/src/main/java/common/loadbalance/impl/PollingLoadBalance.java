package common.loadbalance.impl;

import common.loadbalance.LoadBalance;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class PollingLoadBalance implements LoadBalance {
    private static Map<String, AtomicInteger> pollingMap = new ConcurrentHashMap<>();
    @Override
    public String select(String serverName, List<String> repos) {
        int len = repos.size();
        if (len == 0)
            throw new RuntimeException("未发现注册的服务。");

        if (pollingMap.get(serverName) == null) {
            pollingMap.put(serverName, new AtomicInteger(0));
        }
        int next = pollingMap.get(serverName).incrementAndGet();
        return repos.get(next % repos.size());
    }
}
