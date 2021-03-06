package server;

import common.Protocol;
import common.ProtocolFactory;
import common.URL;
import common.protocol.DubboProtocol;
import common.protocol.HttpProtocol;
import common.protocol.dubbo.NettyServer;
import common.protocol.http.HttpServer;
import common.registry.LocalRegister;
import common.registry.RemoteMapRegister;
import common.registry.ServiceRegisterCenter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import server.service.Arithmetic;
import sun.jvm.hotspot.HelloWorld;

/**
 * @author: changjiale
 * @create: 2019/11/25 21:25
 * @description:
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {

        //SpringApplication.run(Application.class);
        LocalRegister.regist(api.Arithmetic.class.getName(), Arithmetic.class);


        URL url = new URL("127.0.0.1", 8087);
        ServiceRegisterCenter.getInstance().register(api.Arithmetic.class.getName(), url);

        Protocol protocol = ProtocolFactory.getProtocol();
        protocol.start(url);
    }
}
