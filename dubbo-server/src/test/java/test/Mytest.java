package test;

import common.proxy.server.RpcServerProxy;
import common.registry.ServiceRegisterCenter;
import common.registry.impl.ServiceRegisterCenterImpl;
import org.junit.Test;
import server.service.Arithmetic;

/**
 * @author: changjiale
 * @create: 2019/11/25 22:29
 * @description:
 */
public class Mytest {
    @Test
    public void test() throws Exception {
        Arithmetic arithmetic = new Arithmetic();
        ServiceRegisterCenter serviceRegisterCenter = new ServiceRegisterCenterImpl();
        RpcServerProxy rpcServerProxy = new RpcServerProxy(serviceRegisterCenter, "127.0.0.1:8081");
        rpcServerProxy.bind(arithmetic);
        rpcServerProxy.publisher();
    }
}
