package common.loadbalance.impl;

import common.loadbalance.LoadBalance;

import java.util.List;
import java.util.Random;

public class BestRequestLoadBalance implements LoadBalance {
    @Override
    public String select(String serverName, List<String> repos) {
        int len = repos.size();
        if (len == 0)
            throw new RuntimeException("未发现注册的服务。");
        //总权重
        int totalWeight = 0;
        //是否权重一致
        boolean sameWeight = true;
        //先把所有权重加起来，并且判断权重是否一致

        return null;
    }
}
