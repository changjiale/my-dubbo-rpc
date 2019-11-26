package outerApi;

import api.Arithmetic;
import common.proxy.client.RpcClientProxy;
import common.registry.ServiceRegisterCenter;
import common.registry.impl.ServiceRegisterCenterImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: changjiale
 * @create: 2019/11/25 17:34
 * @description:
 */
@RestController
public class Api {

    @GetMapping
    public void sum(Integer a, Integer b) {
        ServiceRegisterCenter serviceDiscover = new ServiceRegisterCenterImpl();
        RpcClientProxy rpcClientProxy = new RpcClientProxy(serviceDiscover);
        Arithmetic api = rpcClientProxy.create(Arithmetic.class);
        System.out.println(api.sum(a, b));
    }

}
