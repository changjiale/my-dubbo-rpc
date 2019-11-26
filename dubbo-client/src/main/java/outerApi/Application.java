package outerApi;

import common.proxy.client.RpcClientProxy;
import common.registry.ServiceRegisterCenter;
import common.registry.impl.ServiceRegisterCenterImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.xml.ws.Service;

/**
 * @author: changjiale
 * @create: 2019/11/25 21:35
 * @description:
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
