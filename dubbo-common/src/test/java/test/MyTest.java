package test;

import common.registry.ServiceRegisterCenter;
import common.registry.impl.ServiceRegisterCenterImpl;
import org.junit.Test;

/**
 * @author: changjiale
 * @create: 2019/11/25 19:58
 * @description:
 */
public class MyTest {

    @Test
    public void test() throws Exception {
        ServiceRegisterCenter serviceRegisterCenter = new ServiceRegisterCenterImpl();
        serviceRegisterCenter.register("leer.dubbo.producer", "127.0.0.1:9091");
        System.in.read();
    }

}
