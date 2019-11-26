package outerApi;

import api.Api;
import common.proxy.client.RpcClientProxy;
import common.registry.ServiceRegisterCenter;
import common.registry.impl.ServiceRegisterCenterImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: changjiale
 * @create: 2019/11/25 17:34
 * @description:
 */
@RestController
public class Arithmetic{

    @GetMapping
    public void sum(Integer a, Integer b) {
        ServiceRegisterCenter serviceRegisterCenter = new ServiceRegisterCenterImpl();
        RpcClientProxy rpcClientProxy = new RpcClientProxy(serviceRegisterCenter);
        Api arithmetic = rpcClientProxy.create(Api.class);
        arithmetic.sum(a, b);
    }

}
