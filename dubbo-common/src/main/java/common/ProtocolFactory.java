package common;

import common.loadbalance.LoadBalance;
import common.protocol.DubboProtocol;
import common.protocol.HttpProtocol;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author: changjiale
 * @create: 2019/12/06 23:24
 * @description:
 */
public class ProtocolFactory {
    public static Protocol getProtocol() {
        //java spi
        ServiceLoader<Protocol> serviceLoader = ServiceLoader.load(Protocol.class);
        Iterator<Protocol> iterator = serviceLoader.iterator();
        return iterator.next();

        //工厂模式
//        String name = System.getProperty("protocolName");
//        if (name == null || name.equals("")) {
//            name = "http";
//        }
//        switch (name) {
//            case "http":
//                return new HttpProtocol();
//            case "dubbo":
//                return new DubboProtocol();
//            default:
//                break;
//        }
//        return new HttpProtocol();
    }
}
