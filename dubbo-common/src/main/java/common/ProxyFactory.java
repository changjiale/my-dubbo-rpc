package common;

import com.alibaba.fastjson.JSONArray;
import common.registry.ServiceRegisterCenter;
import common.request.RpcRequest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author: changjiale
 * @create: 2019/12/05 11:37
 * @description:
 */
public class ProxyFactory {
    public static <T> T getProxy(Class interfaceClass) {
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Protocol protocol = ProtocolFactory.getProtocol();
                RpcRequest rpcRequest = new RpcRequest(interfaceClass.getName(), method.getName(),method.getParameterTypes(), args);

//                URL url = RemoteMapRegister.random(interfaceClass.getName());
                //服务发现，zk进行通讯
                String serviceName = interfaceClass.getName();
                //获取服务实现url地址
                String serviceAddress = ServiceRegisterCenter.getInstance().discover(serviceName);
                URL url = JSONArray.parseObject(serviceAddress, URL.class);

                Object result = protocol.send(url, rpcRequest);

                return result;
            }
        });

    }
}
