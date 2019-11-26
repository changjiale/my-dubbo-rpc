package server;

import common.proxy.server.RpcServerProxy;
import common.registry.ServiceRegisterCenter;
import common.registry.impl.ServiceRegisterCenterImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import server.service.Arithmetic;

/**
 * @author: changjiale
 * @create: 2019/11/25 21:25
 * @description:
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
        Arithmetic arithmetic = new Arithmetic();
        ServiceRegisterCenter serviceRegisterCenter = new ServiceRegisterCenterImpl();
        RpcServerProxy rpcServerProxy = new RpcServerProxy(serviceRegisterCenter, "127.0.0.1:8080");
        rpcServerProxy.bind(arithmetic);
        rpcServerProxy.publisher();
    }
}
