package common;

import common.loadbalance.LoadBalance;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author: changjiale
 * @create: 2019/12/06 23:24
 * @description:
 */
public class LoadBalanceFactory {
    public static LoadBalance getLoadBalance() {
        //选择负载策略
        ServiceLoader<LoadBalance> loadBalanceLoader = ServiceLoader.load(LoadBalance.class);
        Iterator<LoadBalance> iterator = loadBalanceLoader.iterator();
        return iterator.next();

    }
}
