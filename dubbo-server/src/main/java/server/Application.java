package server;

import common.URL;
import common.protocol.http.HttpServer;
import common.registry.LocalRegister;
import common.registry.RemoteMapRegister;
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

        SpringApplication.run(Application.class);
        LocalRegister.regist(Arithmetic.class.getName(), Arithmetic.class);


        URL url = new URL("localhost", 8087);
        RemoteMapRegister.register(Arithmetic.class.getName(), url);

        HttpServer httpServer = new HttpServer();
        httpServer.start("localhost", 8087);
    }
}
